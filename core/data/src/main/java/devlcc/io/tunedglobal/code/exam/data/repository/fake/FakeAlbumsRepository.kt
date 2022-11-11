package devlcc.io.tunedglobal.code.exam.data.repository.fake

import devlcc.io.tunedglobal.code.exam.data.model.toModel
import devlcc.io.tunedglobal.code.exam.data.repository.AlbumsRepository
import devlcc.io.tunedglobal.code.exam.model.Album
import devlcc.io.tunedglobal.code.exam.network.fake.FakeAlbumsDataSource
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException

open class FakeAlbumsRepository(
    private val json: Json
) : AlbumsRepository {

    private var trendingAlbums: List<Album> = emptyList()
    override suspend fun getTrendingAlbums(refresh: Boolean): List<Album> =
        withContext(Dispatchers.IO) {
            delay(100L) // Emulate network fetch delay
            json.decodeFromString<NetworkAlbums>(
                if (refresh) FakeAlbumsDataSource.jsonTrendingAlbumsPage1
                    .also { trendingAlbums = emptyList() }
                else FakeAlbumsDataSource.jsonTrendingAlbumsPage2
            ).let { response ->
                trendingAlbums =
                    (trendingAlbums + response.results.map(NetworkAlbum::toModel)).distinctBy { it.albumID }
                trendingAlbums
            }
        }

    override suspend fun getAlbum(id: Long): Album =
        withContext(Dispatchers.IO) {
            delay(10L) // Emulate network fetch delay
            json.decodeFromString<NetworkAlbum>(FakeAlbumsDataSource.jsonAlbum)
                .let(NetworkAlbum::toModel)
        }

    companion object {
        const val GET_TRENDING_ALBUMS_ACCUMULATED_COUNT_PAGE1 = 10
        const val GET_TRENDING_ALBUMS_ACCUMULATED_COUNT_PAGE2 = 20

        val SAMPLE_ALBUM = FakeAlbumsDataSource.sampleAlbum.toModel()
    }
}

class EmptyAlbumsRepository: AlbumsRepository {
    override suspend fun getTrendingAlbums(refresh: Boolean): List<Album> =
        withContext(Dispatchers.IO) {
            delay(100L) // Emulate network fetch delay
            emptyList()
        }

    override suspend fun getAlbum(id: Long): Album =
        throw IOException()
}

class ErrorAlbumsRepository: AlbumsRepository {
    override suspend fun getTrendingAlbums(refresh: Boolean): List<Album> =
        withContext(Dispatchers.IO) {
            delay(100L) // Emulate network fetch delay
            throw IOException()
        }

    override suspend fun getAlbum(id: Long): Album =
        withContext(Dispatchers.IO) {
            delay(100L) // Emulate network fetch delay
            throw IOException()
        }
}