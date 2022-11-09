package devlcc.io.tunedglobal.code.exam.domain.usecase

import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen

class GetTrendingAlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {

    private var cachedResult: List<Album> = emptyList()

    operator fun invoke(refresh: Boolean = false): Flow<GetTrendingAlbumsResult> = flow {
        emit(GetTrendingAlbumsResult.Loading(refresh))

        val result = albumsRepository.getTrendingAlbums(refresh)

        if (refresh && result.isEmpty()) {
            emit(
                GetTrendingAlbumsResult.Empty
            )

            return@flow
        } else {
            emit(
                GetTrendingAlbumsResult.Success(
                    data = result,
                    hasMoreResults = this@GetTrendingAlbumsUseCase.cachedResult == result
                )
            )

            this@GetTrendingAlbumsUseCase.cachedResult = result
        }
    }.retryWhen { _, attempt ->
        attempt < 3
    }.catch { err ->
        // EMIT Error
        emit(GetTrendingAlbumsResult.Error(err))
    }

}

sealed class GetTrendingAlbumsResult {

    /**
     * Loading on refresh fetch.
     */
    data class Loading(
        val refresh: Boolean,
    ) : GetTrendingAlbumsResult()

    /**
     * Operation success with data.
     */
    data class Success(
        val data: List<Album>,
        // On next paginated fetch attempt, determine whether further results are available.
        val hasMoreResults: Boolean,
    ) : GetTrendingAlbumsResult()

    /**
     * Upon refresh fetch, empty result.
     */
    object Empty : GetTrendingAlbumsResult()

    /**
     * Error encountered.
     */
    data class Error(val err: Throwable) : GetTrendingAlbumsResult()

}