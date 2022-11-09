package devlcc.io.tunedglobal.code.exam.network.retrofit

import androidx.annotation.RestrictTo
import devlcc.io.tunedglobal.code.exam.network.AlbumsNetworkService
import devlcc.io.tunedglobal.code.exam.network.model.GetAlbums
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum
import devlcc.io.tunedglobal.code.exam.network.retrofit.service.AlbumsRetrofitService
import retrofit2.HttpException

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class AlbumsNetworkServiceImpl(
    private val service: AlbumsRetrofitService
) : AlbumsNetworkService {

    override suspend fun getTrendingAlbums(
        offset: Long, count: Long
    ): Pair<GetAlbums.Metadata, GetAlbums.Response> {

        try {
            val result = service.getTrendingAlbums(
                offset = offset,
                count = count,
            )

            return Pair(
                GetAlbums.Metadata(
                    offset = result.offset,
                    count = result.count,
                    total = result.total,
                ), GetAlbums.Response(
                    data = result.results
                )
            )
        } catch (httpError: HttpException) {
            httpError.printStackTrace()

            if (httpError.code() == 404 && offset == 1L) {
                return Pair(
                    GetAlbums.Metadata(
                        offset = offset,
                        count = count,
                        total = count,
                    ), GetAlbums.Response(
                        data = emptyList()
                    )
                )
            } else {
                throw httpError
            }
        } catch (err: Throwable) {
            err.printStackTrace()
            throw err
        }
    }

    override suspend fun getAlbum(id: Long): NetworkAlbum =
        service.getAlbum(id = id)
}