package devlcc.io.tunedglobal.code.exam.data.model

import devlcc.io.tunedglobal.code.exam.model.Artist
import devlcc.io.tunedglobal.code.exam.network.model.NetworkArtist

fun NetworkArtist.toModel(): Artist =
    Artist(
        artistID = this.artistID,
        name = this.name,
    )