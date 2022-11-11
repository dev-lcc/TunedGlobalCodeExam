package devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist

import app.cash.turbine.test
import app.cash.turbine.testIn
import devlcc.io.tunedglobal.code.exam.data.repository.fake.EmptyAlbumsRepository
import devlcc.io.tunedglobal.code.exam.data.repository.fake.ErrorAlbumsRepository
import devlcc.io.tunedglobal.code.exam.data.repository.fake.FakeAlbumsRepository
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetTrendingAlbumsUseCase
import devlcc.io.tunedglobal.code.exam.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class TrendingAlbumListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val albumsRepository = FakeAlbumsRepository(json = Json { ignoreUnknownKeys = true })
    private val getTrendingAlbumsUseCase = GetTrendingAlbumsUseCase(
        albumsRepository = albumsRepository
    )
    private lateinit var viewModel: TrendingAlbumListViewModel

    @Before
    fun setup() {
        viewModel = TrendingAlbumListViewModel(
            getTrendingAlbumsUseCase = getTrendingAlbumsUseCase,
        )
    }

    @Test
    fun `UI State - Loading - Refresh`() = runTest {
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            assertTrue { awaitItem() is TrendingAlbumListState.Success }

            // Perform Refresh
            viewModel.refresh()
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            assertTrue { awaitItem() is TrendingAlbumListState.Success }
        }
    }

    @Test
    fun `UI State - Loading - Fetch More`() = runTest {
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            assertTrue { awaitItem() is TrendingAlbumListState.Success }

            // Perform Fetch More
            viewModel.fetchMore()
            assertEquals(TrendingAlbumListState.Loading(refresh = false), awaitItem())
            assertTrue { awaitItem() is TrendingAlbumListState.Success }
        }
    }

    @Test
    fun `UI State - Success`() = runTest {
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            // Assert first page having first 10 items
            val successInitial = awaitItem() as? TrendingAlbumListState.Success
                ?: fail("Initial Fetch Trending Albums Failed...")
            assertTrue { successInitial.albums.size == FakeAlbumsRepository.GET_TRENDING_ALBUMS_ACCUMULATED_COUNT_PAGE1 }

            // Perform Fetch More
            viewModel.fetchMore()
            skipItems(1)    // Loading

            val successFetchMore = awaitItem() as? TrendingAlbumListState.Success
                ?: fail("Fetch More Trending Albums Failed...")
            // Assert next page accumulating a total of 20 items
            assertTrue { successFetchMore.albums.size == FakeAlbumsRepository.GET_TRENDING_ALBUMS_ACCUMULATED_COUNT_PAGE2 }
        }
    }

    @Test
    fun `UI State - Empty`() = runTest {
        // Setup Empty Repository
        val emptyAlbumsRepository = EmptyAlbumsRepository()
        val emptyGetTrendingAlbumsUse = GetTrendingAlbumsUseCase(
            albumsRepository = emptyAlbumsRepository
        )
        viewModel = TrendingAlbumListViewModel(
            getTrendingAlbumsUseCase = emptyGetTrendingAlbumsUse
        )

        // Assert
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            // Assert Empty List
            assertEquals(TrendingAlbumListState.Empty, awaitItem())

        }
    }

    @Test
    fun `UI State - Error`() = runTest {
        // Setup Empty Repository
        val errorAlbumsRepository = ErrorAlbumsRepository()
        val errorGetTrendingAlbumsUse = GetTrendingAlbumsUseCase(
            albumsRepository = errorAlbumsRepository
        )
        viewModel = TrendingAlbumListViewModel(
            getTrendingAlbumsUseCase = errorGetTrendingAlbumsUse
        )

        val uiEffect = viewModel.uiEffect.testIn(backgroundScope)

        // Assert
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(TrendingAlbumListState.Loading(refresh = true), awaitItem())
            // On Initial Fetch Error, set to EMPTY state
            assertEquals(TrendingAlbumListState.Empty, awaitItem())
            // Then, UI Effect is launched
            assertEquals(TrendingAlbumListEffect.ErrorFetchTrendingAlbums, uiEffect.awaitItem())

        }
    }

}