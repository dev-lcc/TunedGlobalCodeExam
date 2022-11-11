package devlcc.io.tunedglobal.code.exam.feature.albumdetails

import app.cash.turbine.test
import devlcc.io.tunedglobal.code.exam.data.repository.fake.ErrorAlbumsRepository
import devlcc.io.tunedglobal.code.exam.data.repository.fake.FakeAlbumsRepository
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetAlbumDetailsUseCase
import devlcc.io.tunedglobal.code.exam.model.Album
import devlcc.io.tunedglobal.code.exam.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class AlbumDetailsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val albumsRepository = FakeAlbumsRepository(json = Json { ignoreUnknownKeys = true })
    private val getAlbumDetailsUseCase = GetAlbumDetailsUseCase(
        albumsRepository = albumsRepository
    )

    private lateinit var viewModel: AlbumDetailsViewModel

    @Before
    fun setup() {
        viewModel = AlbumDetailsViewModel(
            albumId = TEST_ALBUM.albumID!!,  // any arbitrary ALBUM ID
            getAlbumDetailsUseCase = getAlbumDetailsUseCase,
        )
    }

    @Test
    fun `UI State - Loading`() = runTest {
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            assertTrue { awaitItem() is AlbumDetailsState.Success }

            // Perform Refresh
            viewModel.refresh()
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            assertTrue { awaitItem() is AlbumDetailsState.Success }
        }
    }

    @Test
    fun `UI State - Success`() = runTest {
        viewModel.uiState.test {
            // Initial fetch
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            val successInitial = awaitItem() as? AlbumDetailsState.Success
                ?: fail("Initial Fetch Album Details Failed...")
            assertTrue {
                successInitial.appBarTitle == TEST_ALBUM.name && successInitial.banner == AlbumBanner.Image(
                    TEST_ALBUM.primaryRelease?.image!!
                ) && successInitial.attributes == mutableSetOf<AlbumAttributes>().apply {
                    TEST_ALBUM.primaryRelease?.trackIDS?.size?.takeIf { it > 0 }
                        ?.let { add(AlbumAttributes.SongCount(it)) }
                    TEST_ALBUM.primaryRelease?.allowDownload?.takeIf { it }
                        ?.let { add(AlbumAttributes.Downloadable) }
                    TEST_ALBUM.primaryRelease?.allowStream?.takeIf { it }
                        ?.let { add(AlbumAttributes.Streamable) }
                } && successInitial.recordLabel == TEST_ALBUM.primaryRelease?.label?.name && successInitial.copyright == TEST_ALBUM.primaryRelease?.copyright && successInitial.artists == TEST_ALBUM.artists && successInitial.releaseDate == TEST_ALBUM.primaryRelease?.releaseDate
            }

            // Perform Refresh
            viewModel.refresh()
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            val successRefresh = awaitItem() as? AlbumDetailsState.Success
                ?: fail("Refresh Fetch Album Details Failed...")
            assertTrue {
                successRefresh.appBarTitle == TEST_ALBUM.name && successRefresh.banner == AlbumBanner.Image(
                    TEST_ALBUM.primaryRelease?.image!!
                ) && successRefresh.attributes == mutableSetOf<AlbumAttributes>().apply {
                    TEST_ALBUM.primaryRelease?.trackIDS?.size?.takeIf { it > 0 }
                        ?.let { add(AlbumAttributes.SongCount(it)) }
                    TEST_ALBUM.primaryRelease?.allowDownload?.takeIf { it }
                        ?.let { add(AlbumAttributes.Downloadable) }
                    TEST_ALBUM.primaryRelease?.allowStream?.takeIf { it }
                        ?.let { add(AlbumAttributes.Streamable) }
                } && successRefresh.recordLabel == TEST_ALBUM.primaryRelease?.label?.name && successRefresh.copyright == TEST_ALBUM.primaryRelease?.copyright && successRefresh.artists == TEST_ALBUM.artists && successRefresh.releaseDate == TEST_ALBUM.primaryRelease?.releaseDate
            }
        }
    }

    @Test
    fun `UI State - Error`() = runTest {
        // Setup Empty Repository
        val errorAlbumsRepository = ErrorAlbumsRepository()
        val errorGetAlbumDetailsUseCase = GetAlbumDetailsUseCase(
            albumsRepository = errorAlbumsRepository
        )
        viewModel = AlbumDetailsViewModel(
            albumId = -1L,
            getAlbumDetailsUseCase = errorGetAlbumDetailsUseCase
        )

        viewModel.uiState.test {
            // Initial fetch
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            assertTrue { awaitItem() is AlbumDetailsState.Error }

            // Perform Refresh
            viewModel.refresh()
            assertEquals(AlbumDetailsState.Loading, awaitItem())
            assertTrue { awaitItem() is AlbumDetailsState.Error }
        }
    }

    private val TEST_ALBUM: Album = FakeAlbumsRepository.SAMPLE_ALBUM

}