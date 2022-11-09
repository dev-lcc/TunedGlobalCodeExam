package devlcc.io.tunedglobal.code.exam.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val albumID: Long? = null,
    val name: String? = null,
    val upc: String? = null,
    val artists: List<Artist> = emptyList(),
    val artistTranslations: List<Translation> = emptyList(),
    val albumType: Type? = null,
    val primaryRelease: PrimaryRelease? = null,
    val primaryReleaseID: Long? = null,
    val releaseIds: List<Long> = emptyList(),
    val translations: List<Translation> = emptyList(),
): Parcelable {

    @Parcelize
    enum class Type: Parcelable {
        Album
    }

    @Parcelize
    data class PrimaryRelease (
        val releaseID: Long? = null,
        val albumID: Long? = null,
        val artists: List<Artist> = emptyList(),
        val name: String? = null,
        val isExplicit: Boolean? = null,
        val numberOfVolumes: Long? = null,
        val trackIDS: List<Long> = emptyList(),
        val duration: Long? = null,
        val volumes: List<Volume> = emptyList(),
        val image: String? = null,  // Image URL
        val label: Label? = null,
        val releaseDate: String? = null,    // ISODate String Format i.e. "2019-03-08T00:00:00Z"
        val originalReleaseDate: String? = null,    // ISODate String Format i.e. "2019-03-08T00:00:00Z"
        val allowDownload: Boolean? = null,
        val allowStream: Boolean? = null,
        val contentLanguage: ContentLanguage? = null,
        val copyright: String? = null,
        val translations: List<Translation> = emptyList(),
        val artistTranslations: List<Translation> = emptyList(),
    ): Parcelable

    @Parcelize
    enum class ContentLanguage(val rawValue: String): Parcelable {
        English("ENG"),    // "ENG"
        Dutch("NLD"),    // "NLD"
        Hindu("HIN"),    // "HIN"
        Latin("LAT"),    // "LAT"
        Tagalog("TGL"),    // "TGL"
        Spanish("SPA"),    // "SPA"
    }

    @Parcelize
    data class Label (
        val labelID: String? = null,
        val name: String? = null,
    ): Parcelable

    @Parcelize
    data class Translation (
        val language: Language? = null,
        val value: String? = null,
    ): Parcelable

    @Parcelize
    enum class Language(val rawValue: String): Parcelable {
        English("ENG"),    // "ENG"
        Japanese1("JA"),   // "JA"
        Japanese2("JP"),   // "JP"
        Korean1("KO"),     // "KO"
        Korean2("KR"),     // "KR"
        Greek("EL"),   // "EL"
        Chinese1("CN"),   // "CN"
        Chinese2("ZH"),   // "ZH"
        Hindu("HI"),   // "HI"
        Russian("RU"),   // "RU"
        Thai("TH"),   // "TH"
        Spanish("ES"),   // "ES"
        Dutch("NL"),   // "NL"
    }

    @Parcelize
    data class Volume (
        val firstTrackIndex: Long? = null,
        val lastTrackIndex: Long? = null,
    ): Parcelable

}