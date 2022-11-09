package devlcc.io.tunedglobal.code.exam.network.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class NetworkAlbums(
    @SerialName("Offset")
    val offset: Long? = null,
    @SerialName("Count")
    val count: Long? = null,
    @SerialName("Total")
    val total: Long? = null,
    @SerialName("Results")
    val results: List<NetworkAlbum> = emptyList(),
)

@Serializable
data class NetworkAlbum(
    @SerialName("AlbumId") val albumID: Long? = null,
    @SerialName("Name") val name: String? = null,
    @SerialName("Upc") val upc: String? = null,
    @SerialName("Artists") val artists: List<NetworkArtist> = emptyList(),
    @SerialName("ArtistTranslations") val artistTranslations: List<Translation> = emptyList(),
    @SerialName("AlbumType") val albumType: AlbumType? = null,
    @SerialName("PrimaryRelease") val primaryRelease: PrimaryRelease? = null,
    @SerialName("PrimaryReleaseId") val primaryReleaseID: Long? = null,
    @SerialName("ReleaseIds") val releaseIds: List<Long> = emptyList(),
    @SerialName("Translations") val translations: List<Translation> = emptyList(),
) {

    @Serializable
    enum class AlbumType(val value: String) {
        Album("Album");

        companion object : KSerializer<AlbumType> {
            override val descriptor: SerialDescriptor
                get() {
                    return PrimitiveSerialDescriptor(
                        AlbumType::class.java.simpleName, PrimitiveKind.STRING
                    )
                }

            override fun deserialize(decoder: Decoder): AlbumType =
                when (val value = decoder.decodeString()) {
                    "Album" -> Album
                    else -> throw IllegalArgumentException("AlbumType could not parse: $value")
                }

            override fun serialize(encoder: Encoder, value: AlbumType) {
                return encoder.encodeString(value.value)
            }
        }
    }

    @Serializable
    data class PrimaryRelease(
        @SerialName("ReleaseId") val releaseID: Long? = null,
        @SerialName("AlbumId") val albumID: Long? = null,
        @SerialName("Artists") val artists: List<NetworkArtist> = emptyList(),
        @SerialName("Name") val name: String? = null,
        @SerialName("IsExplicit") val isExplicit: Boolean? = null,
        @SerialName("NumberOfVolumes") val numberOfVolumes: Long? = null,
        @SerialName("TrackIds") val trackIDS: List<Long> = emptyList(),
        @SerialName("Duration") val duration: Long? = null,
        @SerialName("Volumes") val volumes: List<Volume> = emptyList(),
        @SerialName("Image") val image: String? = null,
        @SerialName("Label") val label: Label? = null,
        @SerialName("ReleaseDate") val releaseDate: String? = null,
        @SerialName("OriginalReleaseDate") val originalReleaseDate: String? = null,
        @SerialName("AllowDownload") val allowDownload: Boolean? = null,
        @SerialName("AllowStream") val allowStream: Boolean? = null,
        @SerialName("ContentLanguage") val contentLanguage: ContentLanguage? = null,
        @SerialName("Copyright") val copyright: String? = null,
        @SerialName("Translations") val translations: List<Translation> = emptyList(),
        @SerialName("ArtistTranslations") val artistTranslations: List<Translation> = emptyList()
    )

    @Serializable
    enum class ContentLanguage(val value: String) {
        English("ENG"),    // "ENG"
        Dutch("NLD"),    // "NLD"
        Hindu("HIN"),    // "HIN"
        Latin("LAT"),    // "LAT"
        Tagalog("TGL"),    // "TGL"
        ;

        companion object : KSerializer<ContentLanguage?> {
            override val descriptor: SerialDescriptor
                get() {
                    return PrimitiveSerialDescriptor(
                        ContentLanguage::class.java.simpleName, PrimitiveKind.STRING
                    )
                }

            override fun deserialize(decoder: Decoder): ContentLanguage? =
                when (val value = decoder.decodeString()) {
                    "ENG" -> English
                    "NLD" -> Dutch
                    "HIN" -> Hindu
                    "LAT" -> Latin
                    "TGL" -> Tagalog
                    else -> null
                }

            override fun serialize(encoder: Encoder, value: ContentLanguage?) {
                value?.value?.let(encoder::encodeString)
            }
        }
    }

    @Serializable
    data class Label(
        @SerialName("LabelId") val labelID: String? = null,
        @SerialName("Name") val name: String? = null,
    )

    @Serializable
    data class Translation(
        @SerialName("Language") val language: Language? = null,
        @SerialName("Value") val value: String? = null,
    )

    @Serializable
    enum class Language(val value: String) {
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
        ;

        companion object : KSerializer<Language?> {
            override val descriptor: SerialDescriptor
                get() {
                    return PrimitiveSerialDescriptor(
                        Language::class.java.simpleName, PrimitiveKind.STRING
                    )
                }

            override fun deserialize(decoder: Decoder): Language? =
                when (decoder.decodeString()) {
                    "EN" -> English
                    "JA" -> Japanese1
                    "JP" -> Japanese2
                    "KO" -> Korean1
                    "KR" -> Korean2
                    "EL" -> Greek
                    "CN" -> Chinese1
                    "ZH" -> Chinese2
                    "HI" -> Hindu
                    "RU" -> Russian
                    "TH" -> Thai
                    "ES" -> Spanish
                    "NL" -> Dutch
                    else -> null
                }

            override fun serialize(encoder: Encoder, value: Language?) {
                value?.value?.let(encoder::encodeString)
            }
        }
    }

    @Serializable
    data class Volume(
        @SerialName("FirstTrackIndex") val firstTrackIndex: Long? = null,
        @SerialName("LastTrackIndex") val lastTrackIndex: Long? = null,
    )

}
