package devlcc.io.tunedglobal.code.exam.feature.albumdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Artist
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    private val albumId: Long,
    private val albumsRepository: AlbumsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AlbumDetailsState> =
        MutableStateFlow(AlbumDetailsState.initial())
    val uiState: StateFlow<AlbumDetailsState> = _uiState.asStateFlow()

    private val _uiEffect: MutableSharedFlow<AlbumDetailsEffect> =
        MutableSharedFlow(extraBufferCapacity = 4)
    val uiEffect: SharedFlow<AlbumDetailsEffect> = _uiEffect.asSharedFlow()

    init {
        this.refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            doFetchAlbumDetails(id = albumId)
        }
    }

    private suspend fun doFetchAlbumDetails(id: Long) {

        flow<AlbumDetailsState> {
            val result = albumsRepository.getAlbum(id)

            emit(
                AlbumDetailsState.Success(
                    appBarTitle = result.name ?: "",
                    banner = result.primaryRelease?.image?.let { imageUrl ->
                        AlbumBanner.Image(imageUrl)
                    } ?: AlbumBanner.Placeholder,
                    attributes = mutableSetOf<AlbumAttributes>().apply {
                        val songCount = result.primaryRelease?.trackIDS?.size ?: 0
                        if (songCount > 0) add(AlbumAttributes.SongCount(songCount))

                        val isDownloadable = result.primaryRelease?.allowDownload == true
                        if (isDownloadable) add(AlbumAttributes.Downloadable)

                        val isStreamable = result.primaryRelease?.allowStream == true
                        if (isStreamable) add(AlbumAttributes.Streamable)
                    },
                    recordLabel = result.primaryRelease?.label?.name,
                    copyright = result.primaryRelease?.copyright,
                    artists = result.artists,
                    releaseDate = result.primaryRelease?.releaseDate,
                )
            )
        }.onStart { emit(AlbumDetailsState.Loading) }.retry(3).catch {
            emit(AlbumDetailsState.Error)
        }.collect { result ->
            _uiState.update { result }
        }

    }

}

sealed class AlbumDetailsState {

    object Loading : AlbumDetailsState()

    data class Success(
        val appBarTitle: String,
        val banner: AlbumBanner,
        val attributes: Set<AlbumAttributes> = emptySet(),
        val recordLabel: String? = null,
        val copyright: String? = null,
        val artists: List<Artist> = emptyList(),
        val releaseDate: String? = null,    // ISODate
    ) : AlbumDetailsState()

    object Error : AlbumDetailsState()

    companion object {
        fun initial() = Loading
    }
}

sealed class AlbumBanner {
    data class Image(val url: String) : AlbumBanner()
    object Placeholder : AlbumBanner()
}

sealed class AlbumAttributes {
    data class SongCount(val count: Int) : AlbumAttributes()
    object Downloadable : AlbumAttributes()
    object Streamable : AlbumAttributes()
}

sealed class AlbumDetailsEffect