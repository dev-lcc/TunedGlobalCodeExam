package devlcc.io.tunedglobal.code.exam.data.model

import devlcc.io.tunedglobal.code.exam.model.Album
import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum

fun NetworkAlbum.toModel(): Album =
    Album(
        albumID = this.albumID,
        name = this.name,
        upc = this.upc,
        artists = this.artists.map { it.toModel() },
        artistTranslations = this.artistTranslations.map { it.toModel() },
        albumType = this.albumType?.toModel(),
        primaryRelease = this.primaryRelease?.toModel(),
        primaryReleaseID = this.primaryReleaseID,
        releaseIds = this.releaseIds,
        translations = this.translations.map { it.toModel() },
    )

fun NetworkAlbum.AlbumType.toModel(): Album.Type =
    when(this) {
        NetworkAlbum.AlbumType.Album -> Album.Type.Album
    }

fun NetworkAlbum.PrimaryRelease.toModel(): Album.PrimaryRelease =
    Album.PrimaryRelease(
        releaseID = this.releaseID,
        albumID = this.albumID,
        artists = this.artists.map { it.toModel() },
        name = this.name,
        isExplicit = this.isExplicit,
        numberOfVolumes = this.numberOfVolumes,
        trackIDS = this.trackIDS,
        duration = this.duration,
        volumes = this.volumes.map { it.toModel() },
        image = this.image,
        label = this.label?.toModel(),
        releaseDate = this.releaseDate,
        originalReleaseDate = this.originalReleaseDate,
        allowDownload = this.allowDownload,
        allowStream = this.allowStream,
        contentLanguage = this.contentLanguage?.toModel(),
        copyright = this.copyright,
        translations = this.translations.map { it.toModel() },
        artistTranslations = this.artistTranslations.map { it.toModel() },
    )

fun NetworkAlbum.ContentLanguage.toModel(): Album.ContentLanguage =
    when(this) {
        NetworkAlbum.ContentLanguage.English -> Album.ContentLanguage.English
        NetworkAlbum.ContentLanguage.Dutch -> Album.ContentLanguage.Dutch
        NetworkAlbum.ContentLanguage.Hindu -> Album.ContentLanguage.Hindu
        NetworkAlbum.ContentLanguage.Latin -> Album.ContentLanguage.Latin
        NetworkAlbum.ContentLanguage.Tagalog -> Album.ContentLanguage.Tagalog
    }

fun NetworkAlbum.Label.toModel(): Album.Label =
    Album.Label(
        labelID = this.labelID,
        name = this.name,
    )

fun NetworkAlbum.Translation.toModel(): Album.Translation =
    Album.Translation(
        language = this.language?.toModel(),
        value = this.value,
    )

fun NetworkAlbum.Language.toModel(): Album.Language =
    when(this) {
        NetworkAlbum.Language.English -> Album.Language.English
        NetworkAlbum.Language.Japanese1 -> Album.Language.Japanese1
        NetworkAlbum.Language.Japanese2 -> Album.Language.Japanese2
        NetworkAlbum.Language.Korean1 -> Album.Language.Korean1
        NetworkAlbum.Language.Korean2 -> Album.Language.Korean2
        NetworkAlbum.Language.Greek -> Album.Language.Greek
        NetworkAlbum.Language.Chinese1 -> Album.Language.Chinese1
        NetworkAlbum.Language.Chinese2 -> Album.Language.Chinese2
        NetworkAlbum.Language.Hindu -> Album.Language.Hindu
        NetworkAlbum.Language.Russian -> Album.Language.Russian
        NetworkAlbum.Language.Thai -> Album.Language.Thai
        NetworkAlbum.Language.Spanish -> Album.Language.Spanish
        NetworkAlbum.Language.Dutch -> Album.Language.Dutch
    }

fun NetworkAlbum.Volume.toModel(): Album.Volume =
    Album.Volume(
        firstTrackIndex = this.firstTrackIndex,
        lastTrackIndex = this.lastTrackIndex,
    )