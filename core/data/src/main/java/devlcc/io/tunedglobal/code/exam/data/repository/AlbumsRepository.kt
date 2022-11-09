package devlcc.io.tunedglobal.code.exam.data.repository

import devlcc.io.tunedglobal.code.exam.model.Album

interface AlbumsRepository {

    suspend fun getTrendingAlbums(
        refresh: Boolean = false
    ): List<Album>

    suspend fun getAlbum(id: Long): Album

}