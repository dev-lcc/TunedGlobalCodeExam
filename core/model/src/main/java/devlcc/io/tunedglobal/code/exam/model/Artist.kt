package devlcc.io.tunedglobal.code.exam.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val artistID: Long? = null,
    val name: String? = null,
) : Parcelable
