package devlcc.io.tunedglobal.code.exam.feature.albumdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetAlbumDetailsResult
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetAlbumDetailsUseCase
import devlcc.io.tunedglobal.code.exam.model.Artist
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    private val albumId: Long,
    private val getAlbumDetailsUseCase: GetAlbumDetailsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AlbumDetailsState> =
        MutableStateFlow(AlbumDetailsState.Loading)
    val uiState: StateFlow<AlbumDetailsState> = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), AlbumDetailsState.Loading
    )

    private val _uiEffect: MutableSharedFlow<AlbumDetailsEffect> = MutableSharedFlow()
    val uiEffect: SharedFlow<AlbumDetailsEffect> = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            doFetchAlbumDetails(id = albumId)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            doFetchAlbumDetails(id = albumId)
        }
    }

    private suspend fun doFetchAlbumDetails(id: Long) {
        getAlbumDetailsUseCase(id).collect { result ->
            _uiState.update {
                when (result) {
                    is GetAlbumDetailsResult.Loading -> AlbumDetailsState.Loading
                    is GetAlbumDetailsResult.Success -> AlbumDetailsState.Success(
                        appBarTitle = result.data.name ?: "",
                        banner = result.data.primaryRelease?.image?.let { imageUrl ->
                            AlbumBanner.Image(imageUrl)
                        } ?: AlbumBanner.Placeholder,
                        attributes = mutableSetOf<AlbumAttributes>().apply {
                            val songCount = result.data.primaryRelease?.trackIDS?.size ?: 0
                            if (songCount > 0) add(AlbumAttributes.SongCount(songCount))

                            val isDownloadable = result.data.primaryRelease?.allowDownload == true
                            if (isDownloadable) add(AlbumAttributes.Downloadable)

                            val isStreamable = result.data.primaryRelease?.allowStream == true
                            if (isStreamable) add(AlbumAttributes.Streamable)
                        },
                        recordLabel = result.data.primaryRelease?.label?.name,
                        copyright = result.data.primaryRelease?.copyright,
                        artists = result.data.artists,
                        releaseDate = result.data.primaryRelease?.releaseDate,
                    )
                    is GetAlbumDetailsResult.Error -> AlbumDetailsState.Error
                }
            }
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