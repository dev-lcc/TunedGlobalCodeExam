package devlcc.io.tunedglobal.code.exam.domain.usecase

import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Album
import kotlinx.coroutines.flow.*

class GetAlbumDetailsUseCase(
    private val albumsRepository: AlbumsRepository
) {

    operator fun invoke(albumId: Long): Flow<GetAlbumDetailsResult> = flow<GetAlbumDetailsResult> {
        val result = albumsRepository.getAlbum(albumId)

        emit(
            GetAlbumDetailsResult.Success(
                data = result
            )
        )
    }.onStart { emit(GetAlbumDetailsResult.Loading) }
        // Fallback retry
        .retryWhen { _, attempt ->
            attempt < 3
        }.catch { err ->
            // EMIT Error
            emit(GetAlbumDetailsResult.Error(err))
        }

}

sealed class GetAlbumDetailsResult {

    /**
     * Loading on refresh fetch.
     */
    object Loading : GetAlbumDetailsResult()

    /**
     * Operation success with data.
     */
    data class Success(
        val data: Album
    ) : GetAlbumDetailsResult()

    /**
     * Error encountered.
     */
    data class Error(val err: Throwable) : GetAlbumDetailsResult()

}