package devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetTrendingAlbumsResult
import devlcc.io.tunedglobal.code.exam.domain.usecase.GetTrendingAlbumsUseCase
import devlcc.io.tunedglobal.code.exam.model.Album
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TrendingAlbumListViewModel(
    private val getTrendingAlbumsUseCase: GetTrendingAlbumsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<TrendingAlbumListState> =
        MutableStateFlow(TrendingAlbumListState.Loading(refresh = true))
    val uiState: StateFlow<TrendingAlbumListState> = _uiState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            TrendingAlbumListState.Loading(refresh = true)
        )

    private val _uiEffect: MutableSharedFlow<TrendingAlbumListEffect> = MutableSharedFlow()
    val uiEffect: SharedFlow<TrendingAlbumListEffect> = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            doFetchTrendingAlbums(refresh = true)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            doFetchTrendingAlbums(refresh = true)
        }
    }

    fun fetchMore() {
        viewModelScope.launch {
            doFetchTrendingAlbums(refresh = false)
        }
    }

    private suspend fun doFetchTrendingAlbums(refresh: Boolean) {
        getTrendingAlbumsUseCase(refresh).collect { result ->
            _uiState.update {
                when (result) {
                    is GetTrendingAlbumsResult.Loading -> TrendingAlbumListState.Loading(refresh)
                    is GetTrendingAlbumsResult.Success ->
                        TrendingAlbumListState.Success(albums = result.data)
                    is GetTrendingAlbumsResult.Empty -> TrendingAlbumListState.Empty
                    is GetTrendingAlbumsResult.Error -> TrendingAlbumListState.Empty.also {
                        // Emit Error
                        _uiEffect.emit(
                            TrendingAlbumListEffect.ErrorFetchTrendingAlbums
                        )
                    }
                }
            }
        }
    }

}

sealed class TrendingAlbumListState {
    /**
     * Loading state on both initial fetch(refresh) and fetch more item(s).
     */
    data class Loading(val refresh: Boolean) : TrendingAlbumListState()

    /**
     * UI State to which albums were successfully fetched.
     */
    data class Success(val albums: List<Album>) : TrendingAlbumListState()

    /**
     * UI State to which initial fetch is empty.
     * - If encountered error 404, repository has already handled it, thus, returning an empty list.
     */
    object Empty : TrendingAlbumListState()

}


sealed class TrendingAlbumListEffect {

    /**
     *  Launched Effect if error was encountered on attempt fetch trending albums.
     */
    object ErrorFetchTrendingAlbums : TrendingAlbumListEffect()

}