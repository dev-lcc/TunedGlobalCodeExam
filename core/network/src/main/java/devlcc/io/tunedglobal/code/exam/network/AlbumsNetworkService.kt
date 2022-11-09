package devlcc.io.tunedglobal.code.exam.network

import devlcc.io.tunedglobal.code.exam.network.model.GetAlbums
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum

interface AlbumsNetworkService {

    suspend fun getTrendingAlbums(
        offset: Long = 1L,
        count: Long
    ): Pair<GetAlbums.Metadata, GetAlbums.Response>

    suspend fun getAlbum(id: Long): NetworkAlbum

}