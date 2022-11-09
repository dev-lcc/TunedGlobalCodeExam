package devlcc.io.tunedglobal.code.exam.data.repository.impl

import androidx.annotation.RestrictTo
import devlcc.io.tunedglobal.code.exam.data.model.toModel
import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Album
import devlcc.io.tunedglobal.code.exam.network.AlbumsNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class AlbumsRepositoryImpl(
    private val networkDatasource: AlbumsNetworkService
): AlbumsRepository {

    private var getTrendingAlbumsOffset: Long = INITIAL_OFFSET
    // Mutex to make writes to cached values thread-safe.
    private val trendingAlbumsMutex = Mutex()
    // Cached result
    private var trendingAlbums: List<Album> = emptyList()

    override suspend fun getTrendingAlbums(
        refresh: Boolean
    ): List<Album> {
        if(refresh) {
            getTrendingAlbumsOffset = INITIAL_OFFSET
            // Thread-safe clear cached result
            trendingAlbumsMutex.withLock {
                trendingAlbums = emptyList()
            }
        }

        val (metadata, result) = withContext(Dispatchers.IO) {
            networkDatasource.getTrendingAlbums(
                offset = getTrendingAlbumsOffset,
                count = COUNT
            )
        }

        // Update Current Offset(+= Fetched Count)
        this.getTrendingAlbumsOffset =
            (metadata.offset ?: this.getTrendingAlbumsOffset) +
                    (metadata.count ?: result.data.size).toLong()
        // Insanity Check, make sure offset will NOT exceed total count
        val currentTotal = metadata.total
            ?: (this.trendingAlbums.size + result.data.size).toLong()
        if(this.getTrendingAlbumsOffset > currentTotal) {
            this.getTrendingAlbumsOffset = currentTotal
        }

        // Thread-safe write to cached result
        trendingAlbumsMutex.withLock {
            this.trendingAlbums += result.data.map { it.toModel() }
        }

        return trendingAlbumsMutex.withLock { this.trendingAlbums.distinctBy { it.albumID } }
    }

    // Cached Result
    override suspend fun getAlbum(id: Long): Album =
        withContext(Dispatchers.IO) {
            networkDatasource.getAlbum(id)
                .toModel()
        }

    companion object {
        const val INITIAL_OFFSET = 1L
        const val COUNT = 20L
    }
}