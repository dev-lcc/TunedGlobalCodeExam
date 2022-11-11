package devlcc.io.tunedglobal.code.exam.domain.usecase

import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Album
import kotlinx.coroutines.flow.*

class GetTrendingAlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {

    private var cachedResult: List<Album> = emptyList()

    operator fun invoke(refresh: Boolean = false): Flow<GetTrendingAlbumsResult> = flow {

        val result = albumsRepository.getTrendingAlbums(refresh)

        if (refresh && result.isEmpty()) {
            emit(
                GetTrendingAlbumsResult.Empty
            )
        } else {
            emit(
                GetTrendingAlbumsResult.Success(
                    data = result
                )
            )

            this@GetTrendingAlbumsUseCase.cachedResult = result
        }
    }.onStart { emit(GetTrendingAlbumsResult.Loading(refresh)) }.retryWhen { _, attempt ->
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
     * Error encountered.
     */
    data class Error(val err: Throwable) : GetTrendingAlbumsResult()

}