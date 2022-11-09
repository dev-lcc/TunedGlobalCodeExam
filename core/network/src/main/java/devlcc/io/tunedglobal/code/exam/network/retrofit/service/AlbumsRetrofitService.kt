package devlcc.io.tunedglobal.code.exam.network.retrofit.service

import androidx.annotation.RestrictTo
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbums
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
interface AlbumsRetrofitService {

    @GET(value = "albums/trending")
    suspend fun getTrendingAlbums(
        @Query("offset") offset: Long,
        @Query("count") count: Long,
    ): NetworkAlbums

    @GET(value = "albums/{id}")
    suspend fun getAlbum(
        @Path("id") id: Long,
    ): NetworkAlbum
}