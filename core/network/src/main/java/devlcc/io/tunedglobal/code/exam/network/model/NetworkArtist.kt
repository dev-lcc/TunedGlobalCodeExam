package devlcc.io.tunedglobal.code.exam.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class NetworkArtist(
    @SerialName("ArtistId")
    val artistID: Long? = null,
    @SerialName("Name")
    val name: String? = null,
)
