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
        } else if (result == this@GetTrendingAlbumsUseCase.cachedResult) {
            emit(GetTrendingAlbumsResult.NoMoreResults)
        } else {
            emit(
                GetTrendingAlbumsResult.Success(
                    data = result
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
        val data: List<Album>
    ) : GetTrendingAlbumsResult()

    /**
     * Upon refresh fetch, empty result.
     */
    object Empty : GetTrendingAlbumsResult()

    /**
     * Upon paginated fetch, no further results.
     */
    object NoMoreResults : GetTrendingAlbumsResult()

    /**
     * Error encountered.
     */
    data class Error(val err: Throwable) : GetTrendingAlbumsResult()

}