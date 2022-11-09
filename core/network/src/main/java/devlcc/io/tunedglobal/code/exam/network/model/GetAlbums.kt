package devlcc.io.tunedglobal.code.exam.network.model

object GetAlbums {

    data class Metadata(
        val offset: Long? = null,
        val count: Long? = null,
        val total: Long? = null,
    )

    data class Response(
        val data: List<NetworkAlbum> = emptyList()
    )

}