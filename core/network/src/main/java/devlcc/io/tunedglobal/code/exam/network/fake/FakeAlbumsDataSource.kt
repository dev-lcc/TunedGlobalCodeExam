package devlcc.io.tunedglobal.code.exam.network.fake

import devlcc.io.tunedglobal.code.exam.network.model.NetworkAlbum
import devlcc.io.tunedglobal.code.exam.network.model.NetworkArtist
import org.intellij.lang.annotations.Language

object FakeAlbumsDataSource {

    val sampleAlbum = NetworkAlbum(
        albumID = 108859186,
        name = "Reflections",
        upc = "00028948365791",
        artists = listOf(
            NetworkArtist(
                artistID = 5619,
                name = "Anoushka Shankar",
            )
        ),
        artistTranslations = emptyList(),
        albumType = NetworkAlbum.AlbumType.Album,
        primaryRelease = NetworkAlbum.PrimaryRelease(
            releaseID = 108859186L,
            albumID = 108859186L,
            artists = listOf(
                NetworkArtist(
                    artistID = 5619,
                    name = "Anoushka Shankar",
                )
            ),
            name = "Reflections",
            isExplicit = false,
            numberOfVolumes = 1,
            trackIDS = listOf(
                108859187L,
                108859189L,
                108859190L,
                108859191L,
                108859192L,
                108859193L,
                108859194L,
                108859195L,
                108859196L,
                108859197L,
                108859198L,
                108859199L,
                108859200L,
                108859201L,
                108859188L,
            ),
            duration = 4475L,
            volumes = listOf(
                NetworkAlbum.Volume(firstTrackIndex = 0, lastTrackIndex = 14)
            ),
            image = "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/002/894/836/579/1/104_1004_00028948365791_20210419_0246.jpg",
            label = NetworkAlbum.Label(
                labelID = "865",
                name = "Deutsche Grammophon (DG)",
            ),
            releaseDate = "2019-03-08T00:00:00Z",
            originalReleaseDate = "2019-03-08T00:00:00Z",
            allowDownload = true,
            allowStream = true,
            contentLanguage = NetworkAlbum.ContentLanguage.HIN,
            copyright = "2019 Deutsche Grammophon GmbH, Berlin This Compilation ? 2019 Deutsche Grammophon GmbH, Berlin",
            translations = listOf(
                NetworkAlbum.Translation(
                    language = NetworkAlbum.Language.EN,
                    value = "Reflections",
                )
            ),
            artistTranslations = emptyList(),
        ),
        primaryReleaseID = 108859186L,
        releaseIds = listOf(108859186),
        translations = listOf(
            NetworkAlbum.Translation(
                language = NetworkAlbum.Language.EN,
                value = "Reflections",
            )
        ),
    )

    @Language("JSON")
    val trendingAlbumsAccumulatedCountPage1 = 10
    val jsonTrendingAlbumsPage1 = """
{
    "Offset": 1,
    "Count": 10,
    "Total": 20,
    "Results": [
        {
            "AlbumId": 108859186,
            "Name": "Reflections",
            "Upc": "00028948365791",
            "Artists": [
                {
                    "ArtistId": 5619,
                    "Name": "Anoushka Shankar"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 108859186,
                "AlbumId": 108859186,
                "Artists": [
                    {
                        "ArtistId": 5619,
                        "Name": "Anoushka Shankar"
                    }
                ],
                "Name": "Reflections",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    108859187,
                    108859188,
                    108859189,
                    108859190,
                    108859191,
                    108859192,
                    108859193,
                    108859194,
                    108859195,
                    108859196,
                    108859197,
                    108859198,
                    108859199,
                    108859200,
                    108859201
                ],
                "Duration": 4475,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/002/894/836/579/1/104_1004_00028948365791_20210419_0246.jpg",
                "Label": {
                    "LabelId": "865",
                    "Name": "Deutsche Grammophon (DG)"
                },
                "ReleaseDate": "2019-03-08T00:00:00Z",
                "OriginalReleaseDate": "2019-03-08T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "HIN",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Reflections"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 108859186,
            "ReleaseIds": [
                108859186
            ],
            "Translations": []
        },
        {
            "AlbumId": 112245815,
            "Name": "Shawn Mendes [Deluxe]",
            "Upc": "00602577844669",
            "Artists": [
                {
                    "ArtistId": 1625886,
                    "Name": "Shawn Mendes"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 112245815,
                "AlbumId": 112245815,
                "Artists": [
                    {
                        "ArtistId": 1625886,
                        "Name": "Shawn Mendes"
                    }
                ],
                "Name": "Shawn Mendes [Deluxe]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    112245816,
                    112245817,
                    112245818,
                    112245819,
                    112245820,
                    112245821,
                    112245822,
                    112245823,
                    112245824,
                    112245825,
                    112245826,
                    112245827,
                    112245828,
                    112245829,
                    112245830,
                    112245831
                ],
                "Duration": 3039,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/257/784/466/9/104_1004_00602577844669_20220222_0953.jpg",
                "Label": {
                    "LabelId": "2892",
                    "Name": "Island Records"
                },
                "ReleaseDate": "2019-07-26T00:00:00Z",
                "OriginalReleaseDate": "2019-07-26T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Shawn Mendes [Deluxe]"
                    },
                    {
                        "Language": "JA",
                        "Value": "ショーンメンデス [デラックス]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 112245815,
            "ReleaseIds": [
                112245815
            ],
            "Translations": []
        },
        {
            "AlbumId": 88421104,
            "Name": "There's Nothing Holdin' Me Back [NOTD Remix]",
            "Upc": "00602557685473",
            "Artists": [
                {
                    "ArtistId": 1625886,
                    "Name": "Shawn Mendes"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 88421104,
                "AlbumId": 88421104,
                "Artists": [
                    {
                        "ArtistId": 1625886,
                        "Name": "Shawn Mendes"
                    }
                ],
                "Name": "There's Nothing Holdin' Me Back [NOTD Remix]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    88421105
                ],
                "Duration": 194,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/255/768/547/3/104_1004_00602557685473_20220222_0506.jpg",
                "Label": {
                    "LabelId": "2892",
                    "Name": "Island Records"
                },
                "ReleaseDate": "2017-05-10T00:00:00Z",
                "OriginalReleaseDate": "2017-05-10T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "There's Nothing Holdin' Me Back [NOTD Remix]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 88421104,
            "ReleaseIds": [
                88421104
            ],
            "Translations": []
        },
        {
            "AlbumId": 65333038,
            "Name": "Atlas [From “The Hunger Games: Catching Fire” Soundtrack]",
            "Upc": "00602537538959",
            "Artists": [
                {
                    "ArtistId": 1001,
                    "Name": "Coldplay"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65333038,
                "AlbumId": 65333038,
                "Artists": [
                    {
                        "ArtistId": 1001,
                        "Name": "Coldplay"
                    }
                ],
                "Name": "Atlas [From “The Hunger Games: Catching Fire” Soundtrack]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    68206322
                ],
                "Duration": 236,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/253/753/895/9/104_1004_00602537538959_20220222_1136.jpg",
                "Label": {
                    "LabelId": "96037",
                    "Name": "Hunger Games 2/Catching Fire"
                },
                "ReleaseDate": "2013-09-09T00:00:00Z",
                "OriginalReleaseDate": "2013-09-09T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Atlas [From “The Hunger Games: Catching Fire” Soundtrack]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65333038,
            "ReleaseIds": [
                65333038
            ],
            "Translations": []
        },
        {
            "AlbumId": 65402457,
            "Name": "Passive Me Aggressive You",
            "Upc": "09416339813593",
            "Artists": [
                {
                    "ArtistId": 168332,
                    "Name": "The Naked And Famous"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65402457,
                "AlbumId": 65402457,
                "Artists": [
                    {
                        "ArtistId": 168332,
                        "Name": "The Naked And Famous"
                    }
                ],
                "Name": "Passive Me Aggressive You",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    65731043,
                    65731044,
                    65731045,
                    65731046,
                    65731047,
                    65731048,
                    66743673,
                    66746095,
                    66746096,
                    66746097,
                    66746098,
                    66746099,
                    66746100
                ],
                "Duration": 2958,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/941/633/981/359/3/104_1004_09416339813593_20210219_0324.jpg",
                "Label": {
                    "LabelId": "3375",
                    "Name": "Universal Music New Zealand Limited"
                },
                "ReleaseDate": "2010-09-06T00:00:00Z",
                "OriginalReleaseDate": "2010-09-06T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Passive Me Aggressive You"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65402457,
            "ReleaseIds": [
                65402457
            ],
            "Translations": []
        },
        {
            "AlbumId": 65339704,
            "Name": "1989",
            "Upc": "00602547066886",
            "Artists": [
                {
                    "ArtistId": 1477,
                    "Name": "Taylor Swift"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65339704,
                "AlbumId": 65339704,
                "Artists": [
                    {
                        "ArtistId": 1477,
                        "Name": "Taylor Swift"
                    }
                ],
                "Name": "1989",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    65724470,
                    65724471,
                    65724472,
                    65724473,
                    65724474,
                    65724475,
                    65724476,
                    66133317,
                    66551087,
                    66551088,
                    66551089,
                    66551090,
                    66551091
                ],
                "Duration": 2928,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/254/706/688/6/104_1004_00602547066886_20210430_1731.jpg",
                "Label": {
                    "LabelId": "631",
                    "Name": "Big Machine Records, LLC"
                },
                "ReleaseDate": "2014-10-27T00:00:00Z",
                "OriginalReleaseDate": "2014-10-27T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "1989"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65339704,
            "ReleaseIds": [
                65339704
            ],
            "Translations": []
        },
        {
            "AlbumId": 65384523,
            "Name": "Live At Carnegie Hall [Live]",
            "Upc": "00724353492250",
            "Artists": [
                {
                    "ArtistId": 5619,
                    "Name": "Anoushka Shankar"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65384523,
                "AlbumId": 65384523,
                "Artists": [
                    {
                        "ArtistId": 5619,
                        "Name": "Anoushka Shankar"
                    }
                ],
                "Name": "Live At Carnegie Hall [Live]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    66545901,
                    66545902,
                    66545903,
                    66545904,
                    66545905,
                    66545906
                ],
                "Duration": 3608,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/072/435/349/225/0/104_1004_00724353492250_20220222_1201.jpg",
                "Label": {
                    "LabelId": "215546",
                    "Name": "Warner Classics (Parlophone)"
                },
                "ReleaseDate": "2001-10-23T00:00:00Z",
                "OriginalReleaseDate": "2001-10-23T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Live At Carnegie Hall [Live]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65384523,
            "ReleaseIds": [
                65384523
            ],
            "Translations": []
        },
        {
            "AlbumId": 83193325,
            "Name": "101 90s For Mum",
            "Upc": "00600753771334",
            "Artists": [
                {
                    "ArtistId": 142807,
                    "Name": "Various Artists"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 83193325,
                "AlbumId": 83193325,
                "Artists": [
                    {
                        "ArtistId": 142807,
                        "Name": "Various Artists"
                    }
                ],
                "Name": "101 90s For Mum",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    83193329,
                    83193332,
                    83193333,
                    83193334,
                    83193335,
                    83193336,
                    83193338,
                    83193340,
                    83193342,
                    83193343,
                    83193347,
                    83193351,
                    83193353,
                    83193354,
                    83193355,
                    83193356,
                    83193357,
                    83193358,
                    83193360,
                    83193362,
                    83193363,
                    83193365,
                    83193367,
                    83193368,
                    83193369,
                    83193371,
                    83193372,
                    83193374,
                    83193376,
                    83193377,
                    83193378,
                    83193387,
                    83193390,
                    83193393,
                    83193394,
                    83193396,
                    83193397,
                    83193398,
                    83193400,
                    83193401,
                    83193402,
                    83193407,
                    83193408,
                    83193409,
                    83193410,
                    83193411,
                    83193412,
                    83193413,
                    83193419,
                    83193420,
                    83193421
                ],
                "Duration": 12222,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/075/377/133/4/104_1004_00600753771334_0_20170504_1109.jpg",
                "Label": {
                    "LabelId": "2777",
                    "Name": "Universal Music Australia Pty. Ltd."
                },
                "ReleaseDate": "2017-04-07T00:00:00Z",
                "OriginalReleaseDate": "2017-04-07T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "101 90s For Mum"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 83193325,
            "ReleaseIds": [
                83193325
            ],
            "Translations": []
        },
        {
            "AlbumId": 109101411,
            "Name": "Wow. [Remix]",
            "Upc": "00602577634352",
            "Artists": [
                {
                    "ArtistId": 1883241,
                    "Name": "Post Malone"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 109101411,
                "AlbumId": 109101411,
                "Artists": [
                    {
                        "ArtistId": 1883241,
                        "Name": "Post Malone"
                    }
                ],
                "Name": "Wow. [Remix]",
                "IsExplicit": true,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    109101412
                ],
                "Duration": 170,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/257/763/435/2/104_1004_00602577634352_20210304_0520.jpg",
                "Label": {
                    "LabelId": "3986",
                    "Name": "Republic Records"
                },
                "ReleaseDate": "2019-03-15T00:00:00Z",
                "OriginalReleaseDate": "2019-03-15T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Wow. [Remix]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 109101411,
            "ReleaseIds": [
                109101411
            ],
            "Translations": []
        },
        {
            "AlbumId": 95698222,
            "Name": "Rebel Heart Tour [Live]",
            "Upc": "00670211032802",
            "Artists": [
                {
                    "ArtistId": 1203,
                    "Name": "Madonna"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 95698222,
                "AlbumId": 95698222,
                "Artists": [
                    {
                        "ArtistId": 1203,
                        "Name": "Madonna"
                    }
                ],
                "Name": "Rebel Heart Tour [Live]",
                "IsExplicit": true,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    95698223,
                    95698224,
                    95698225,
                    95698226,
                    95698227,
                    95698228,
                    95698229,
                    95698230,
                    95698231,
                    95698232,
                    95698233,
                    95698234,
                    95698235,
                    95698236,
                    95698237,
                    95698238,
                    95698239,
                    95698240,
                    95698241,
                    95698242,
                    95698243,
                    95698244
                ],
                "Duration": 5950,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/067/021/103/280/2/104_1004_00670211032802_20210216_0733.jpg",
                "Label": {
                    "LabelId": "130020",
                    "Name": "Mercury Studios"
                },
                "ReleaseDate": "2017-09-15T00:00:00Z",
                "OriginalReleaseDate": "2017-09-15T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Rebel Heart Tour [Live]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 95698222,
            "ReleaseIds": [
                95698222
            ],
            "Translations": []
        }
    ]
}
    """.trimIndent()

    val trendingAlbumsAccumulatedCountPage2 = 20
    @Language("JSON")
    val jsonTrendingAlbumsPage2 = """
{
    "Offset": 11,
    "Count": 10,
    "Total": 20,
    "Results": [
        {
            "AlbumId": 104290547,
            "Name": "Machine Dreams",
            "Upc": "05099994700350",
            "Artists": [
                {
                    "ArtistId": 42368,
                    "Name": "Little Dragon"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 104290547,
                "AlbumId": 104290547,
                "Artists": [
                    {
                        "ArtistId": 42368,
                        "Name": "Little Dragon"
                    }
                ],
                "Name": "Machine Dreams",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    104290548,
                    104290549,
                    104290550,
                    104290551,
                    104290552,
                    104290553,
                    104290554,
                    104290555,
                    104290556,
                    104290557,
                    104290558
                ],
                "Duration": 2643,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/509/999/470/035/0/104_1004_05099994700350_0_20180913_1747.jpg",
                "Label": {
                    "LabelId": "3219",
                    "Name": "Virgin Records"
                },
                "ReleaseDate": "2010-11-26T00:00:00Z",
                "OriginalReleaseDate": "2010-11-26T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Machine Dreams"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 104290547,
            "ReleaseIds": [
                104290547
            ],
            "Translations": []
        },
        {
            "AlbumId": 65311683,
            "Name": "Def Jam Recordings Presents Music Inspired By Scarface",
            "Upc": "00602498608678",
            "Artists": [
                {
                    "ArtistId": 142807,
                    "Name": "Various Artists"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65311683,
                "AlbumId": 65311683,
                "Artists": [
                    {
                        "ArtistId": 142807,
                        "Name": "Various Artists"
                    }
                ],
                "Name": "Def Jam Recordings Presents Music Inspired By Scarface",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    66061346,
                    66061347,
                    66061348,
                    66061349,
                    66061350,
                    66897128
                ],
                "Duration": 1409,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/006/024/986/086/78/104_00602498608678_24072016_1020.jpg",
                "Label": {
                    "LabelId": null,
                    "Name": null
                },
                "ReleaseDate": "2007-10-25T00:00:00Z",
                "OriginalReleaseDate": "2007-10-25T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Def Jam Recordings Presents Music Inspired By Scarface"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65311683,
            "ReleaseIds": [
                65311683
            ],
            "Translations": []
        },
        {
            "AlbumId": 65310085,
            "Name": "Thirsty Work",
            "Upc": "00602498341223",
            "Artists": [
                {
                    "ArtistId": 66326,
                    "Name": "Status Quo"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65310085,
                "AlbumId": 65310085,
                "Artists": [
                    {
                        "ArtistId": 66326,
                        "Name": "Status Quo"
                    }
                ],
                "Name": "Thirsty Work",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    66051541,
                    66051542,
                    66051543,
                    66051544,
                    66051545,
                    66051546,
                    66051547,
                    66051548,
                    66051549,
                    66051550,
                    67167631,
                    67167632,
                    67167633,
                    67167634,
                    67167635,
                    67167636,
                    67167637,
                    67167638,
                    68116871,
                    68116872,
                    68116873
                ],
                "Duration": 4596,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/249/834/122/3/104_1004_00602498341223_20210225_1848.jpg",
                "Label": {
                    "LabelId": "3952",
                    "Name": "UMC (Universal Music Catalogue)"
                },
                "ReleaseDate": "2006-01-30T00:00:00Z",
                "OriginalReleaseDate": "2006-01-30T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Thirsty Work"
                    },
                    {
                        "Language": "JA",
                        "Value": "Thirsty Work"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65310085,
            "ReleaseIds": [
                65310085
            ],
            "Translations": []
        },
        {
            "AlbumId": 108064335,
            "Name": "I Give It A Year [Original Soundtrack]",
            "Upc": "00602537292844",
            "Artists": [
                {
                    "ArtistId": 142807,
                    "Name": "Various Artists"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 108064335,
                "AlbumId": 108064335,
                "Artists": [
                    {
                        "ArtistId": 142807,
                        "Name": "Various Artists"
                    }
                ],
                "Name": "I Give It A Year [Original Soundtrack]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    108064336,
                    108064337,
                    108064338,
                    108064339,
                    108064340,
                    108064341,
                    108064342,
                    108064343,
                    108064344,
                    108064345,
                    108064346,
                    108064347,
                    108064348,
                    108064349,
                    108064350
                ],
                "Duration": 3545,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/060/253/729/284/4/104_1004_00602537292844_20210517_1523.jpg",
                "Label": {
                    "LabelId": "3969",
                    "Name": "Universal-Island Records Ltd."
                },
                "ReleaseDate": "2013-02-04T00:00:00Z",
                "OriginalReleaseDate": "2013-02-04T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "I Give It A Year [Original Soundtrack]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 108064335,
            "ReleaseIds": [
                108064335
            ],
            "Translations": []
        },
        {
            "AlbumId": 65284836,
            "Name": "Out Of Our Heads",
            "Upc": "00018771942924",
            "Artists": [
                {
                    "ArtistId": 139070,
                    "Name": "The Rolling Stones"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65284836,
                "AlbumId": 65284836,
                "Artists": [
                    {
                        "ArtistId": 139070,
                        "Name": "The Rolling Stones"
                    }
                ],
                "Name": "Out Of Our Heads",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    65877879,
                    65877880,
                    65877881,
                    65877882,
                    65877883,
                    67769299,
                    67769300,
                    67769301,
                    67769302,
                    67769303,
                    67769304,
                    67769305
                ],
                "Duration": 2008,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/001/877/194/292/4/104_1004_00018771942924_20210420_0228.jpg",
                "Label": {
                    "LabelId": "14260",
                    "Name": "ABKCO Music and Records, Inc."
                },
                "ReleaseDate": "2011-02-21T00:00:00Z",
                "OriginalReleaseDate": "2011-02-21T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Out Of Our Heads"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65284836,
            "ReleaseIds": [
                65284836
            ],
            "Translations": []
        },
        {
            "AlbumId": 65291959,
            "Name": "Mozart: Rarities & Surprises [Complete Mozart Edition]",
            "Upc": "00028947573838",
            "Artists": [
                {
                    "ArtistId": 142807,
                    "Name": "Various Artists"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65291959,
                "AlbumId": 65291959,
                "Artists": [
                    {
                        "ArtistId": 142807,
                        "Name": "Various Artists"
                    }
                ],
                "Name": "Mozart: Rarities & Surprises [Complete Mozart Edition]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    65955529,
                    65955530,
                    65955531,
                    65955532,
                    65955533,
                    65955534,
                    65955535,
                    65955536,
                    65955537,
                    65955538,
                    65955539,
                    65955540,
                    65955541,
                    65955542,
                    65955543,
                    65955544,
                    65955545,
                    65955546,
                    65955547,
                    65955548,
                    65955549,
                    65955550,
                    65955551,
                    65955552,
                    65955553,
                    65955554,
                    65955555,
                    65955556,
                    66222712,
                    66222713,
                    66222714,
                    66222715,
                    66222716,
                    66222717,
                    66456738,
                    66456739,
                    66456740,
                    66456741,
                    66456742,
                    66456743,
                    66456744,
                    66456745,
                    66456746,
                    66456747,
                    66456748,
                    66456749,
                    66456750,
                    66456751,
                    66456752,
                    66456753,
                    66456754,
                    66456755,
                    66456756,
                    67483760,
                    67483761,
                    67483762,
                    67483763,
                    67554260
                ],
                "Duration": 9237,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/002/894/757/383/8/104_1004_00028947573838_20210313_1516.jpg",
                "Label": {
                    "LabelId": "3977",
                    "Name": "Decca Music Group Ltd."
                },
                "ReleaseDate": "2006-02-14T00:00:00Z",
                "OriginalReleaseDate": "2006-02-14T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "LAT",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Mozart: Rarities & Surprises [Complete Mozart Edition]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65291959,
            "ReleaseIds": [
                65291959
            ],
            "Translations": []
        },
        {
            "AlbumId": 78723884,
            "Name": "Land Of Gold [Remixes]",
            "Upc": "00028947965381",
            "Artists": [
                {
                    "ArtistId": 5619,
                    "Name": "Anoushka Shankar"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 78723884,
                "AlbumId": 78723884,
                "Artists": [
                    {
                        "ArtistId": 5619,
                        "Name": "Anoushka Shankar"
                    }
                ],
                "Name": "Land Of Gold [Remixes]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    78723885,
                    78723886,
                    78723887,
                    78723888,
                    78723889,
                    78723890,
                    78723891
                ],
                "Duration": 2499,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/002/894/796/538/1/104_1004_00028947965381_20220222_0257.jpg",
                "Label": {
                    "LabelId": "865",
                    "Name": "Deutsche Grammophon (DG)"
                },
                "ReleaseDate": "2016-09-30T00:00:00Z",
                "OriginalReleaseDate": "2016-09-30T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Land Of Gold [Remixes]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 78723884,
            "ReleaseIds": [
                78723884
            ],
            "Translations": []
        },
        {
            "AlbumId": 65297473,
            "Name": "Fantastic 4 [Original Motion Picture Score]",
            "Upc": "00030206666793",
            "Artists": [
                {
                    "ArtistId": 35730,
                    "Name": "John Ottman"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65297473,
                "AlbumId": 65297473,
                "Artists": [
                    {
                        "ArtistId": 35730,
                        "Name": "John Ottman"
                    }
                ],
                "Name": "Fantastic 4 [Original Motion Picture Score]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    67636421,
                    67636422,
                    67636423,
                    67636424,
                    67636425,
                    67636426,
                    67636427,
                    67636428,
                    67636429,
                    67636430,
                    67636431,
                    67636432,
                    67636433,
                    67660860
                ],
                "Duration": 2733,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/000/302/066/667/93/UMG_00030206666793_T43_cvrart.jpg",
                "Label": {
                    "LabelId": "3000",
                    "Name": "Varese Sarabande"
                },
                "ReleaseDate": "2014-05-16T00:00:00Z",
                "OriginalReleaseDate": "2014-05-16T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Fantastic 4 [Original Motion Picture Score]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65297473,
            "ReleaseIds": [
                65297473
            ],
            "Translations": []
        },
        {
            "AlbumId": 65297659,
            "Name": "My Sister's Keeper [Original Motion Picture Score]",
            "Upc": "00030206697797",
            "Artists": [
                {
                    "ArtistId": 2116,
                    "Name": "Aaron Zigman"
                }
            ],
            "ArtistTranslations": [],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65297659,
                "AlbumId": 65297659,
                "Artists": [
                    {
                        "ArtistId": 2116,
                        "Name": "Aaron Zigman"
                    }
                ],
                "Name": "My Sister's Keeper [Original Motion Picture Score]",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    67595923,
                    67595924,
                    67595925,
                    67595926,
                    67595927,
                    67595928,
                    67595929,
                    67595930,
                    67595931,
                    67595932,
                    67595933,
                    67595934,
                    67595935,
                    67595936,
                    67595937,
                    67595938,
                    67595939,
                    67595940
                ],
                "Duration": 2064,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/003/020/669/779/7/104_1004_00030206697797_20210331_1308.jpg",
                "Label": {
                    "LabelId": "3431",
                    "Name": "Varese"
                },
                "ReleaseDate": "2014-05-16T00:00:00Z",
                "OriginalReleaseDate": "2014-05-16T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": null,
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "My Sister's Keeper [Original Motion Picture Score]"
                    }
                ],
                "ArtistTranslations": []
            },
            "PrimaryReleaseId": 65297659,
            "ReleaseIds": [
                65297659
            ],
            "Translations": []
        },
        {
            "AlbumId": 65301962,
            "Name": "Shake It Up: I <3 Dance",
            "Upc": "00050087291693",
            "Artists": [
                {
                    "ArtistId": 142807,
                    "Name": "Various Artists"
                }
            ],
            "ArtistTranslations": [
                {
                    "ArtistId": 142807,
                    "Translations": [
                        {
                            "Language": "CN",
                            "Value": "群星"
                        },
                        {
                            "Language": "EL",
                            "Value": "Διάφοροι Καλλιτέχνες"
                        },
                        {
                            "Language": "EN",
                            "Value": "Various Artists"
                        },
                        {
                            "Language": "HI",
                            "Value": "वेरियस आर्टिस्ट्स"
                        },
                        {
                            "Language": "JA",
                            "Value": "Various Artists,ヴァリアス"
                        },
                        {
                            "Language": "JP",
                            "Value": "ヴァリアスアーティスト"
                        },
                        {
                            "Language": "KO",
                            "Value": "Various Artists"
                        },
                        {
                            "Language": "KR",
                            "Value": "현영"
                        },
                        {
                            "Language": "RU",
                            "Value": "Разные исполнители"
                        },
                        {
                            "Language": "TH",
                            "Value": "รวมศิลปิน"
                        },
                        {
                            "Language": "ZH",
                            "Value": "群星,群星"
                        }
                    ]
                }
            ],
            "AlbumType": "Album",
            "PrimaryRelease": {
                "ReleaseId": 65301962,
                "AlbumId": 65301962,
                "Artists": [
                    {
                        "ArtistId": 142807,
                        "Name": "Various Artists"
                    }
                ],
                "Name": "Shake It Up: I <3 Dance",
                "IsExplicit": false,
                "NumberOfVolumes": 1,
                "TrackIds": [
                    67784225,
                    67784226,
                    67784227,
                    67784228,
                    67784229,
                    67784230,
                    67791679,
                    67791680,
                    67791681,
                    67791682,
                    67791683,
                    67872079
                ],
                "Duration": 2182,
                "Volumes": [
                    {
                        "FirstTrackIndex": 0,
                        "LastTrackIndex": 0
                    }
                ],
                "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/005/008/729/169/3/104_1004_00050087291693_20220222_0221.jpg",
                "Label": {
                    "LabelId": "3381",
                    "Name": "Walt Disney Records"
                },
                "ReleaseDate": "2013-03-05T00:00:00Z",
                "OriginalReleaseDate": "2013-03-05T00:00:00Z",
                "AllowDownload": true,
                "AllowStream": true,
                "ContentLanguage": "ENG",
                "Copyright": null,
                "Translations": [
                    {
                        "Language": "EN",
                        "Value": "Shake It Up: I <3 Dance"
                    }
                ],
                "ArtistTranslations": [
                    {
                        "ArtistId": 142807,
                        "Translations": [
                            {
                                "Language": "CN",
                                "Value": "群星"
                            },
                            {
                                "Language": "EL",
                                "Value": "Διάφοροι Καλλιτέχνες"
                            },
                            {
                                "Language": "EN",
                                "Value": "Various Artists"
                            },
                            {
                                "Language": "HI",
                                "Value": "वेरियस आर्टिस्ट्स"
                            },
                            {
                                "Language": "JA",
                                "Value": "Various Artists,ヴァリアス"
                            },
                            {
                                "Language": "JP",
                                "Value": "ヴァリアスアーティスト"
                            },
                            {
                                "Language": "KO",
                                "Value": "Various Artists"
                            },
                            {
                                "Language": "KR",
                                "Value": "현영"
                            },
                            {
                                "Language": "RU",
                                "Value": "Разные исполнители"
                            },
                            {
                                "Language": "TH",
                                "Value": "รวมศิลปิน"
                            },
                            {
                                "Language": "ZH",
                                "Value": "群星,群星"
                            }
                        ]
                    }
                ]
            },
            "PrimaryReleaseId": 65301962,
            "ReleaseIds": [
                65301962
            ],
            "Translations": []
        }
    ]
}
    """.trimIndent()

    @Language("JSON")
    val jsonAlbum = """
{
    "AlbumId": 108859186,
    "Name": "Reflections",
    "Upc": "00028948365791",
    "Artists": [
        {
            "ArtistId": 5619,
            "Name": "Anoushka Shankar"
        }
    ],
    "ArtistTranslations": [],
    "AlbumType": "Album",
    "PrimaryRelease": {
        "ReleaseId": 108859186,
        "AlbumId": 108859186,
        "Artists": [
            {
                "ArtistId": 5619,
                "Name": "Anoushka Shankar"
            }
        ],
        "Name": "Reflections",
        "IsExplicit": false,
        "NumberOfVolumes": 1,
        "TrackIds": [
            108859187,
            108859189,
            108859190,
            108859191,
            108859192,
            108859193,
            108859194,
            108859195,
            108859196,
            108859197,
            108859198,
            108859199,
            108859200,
            108859201,
            108859188
        ],
        "Duration": 4475,
        "Volumes": [
            {
                "FirstTrackIndex": 0,
                "LastTrackIndex": 14
            }
        ],
        "Image": "https://d16npyvi7pcxgr.cloudfront.net/images1004/100/4_0/002/894/836/579/1/104_1004_00028948365791_20210419_0246.jpg",
        "Label": {
            "LabelId": "865",
            "Name": "Deutsche Grammophon (DG)"
        },
        "ReleaseDate": "2019-03-08T00:00:00Z",
        "OriginalReleaseDate": "2019-03-08T00:00:00Z",
        "AllowDownload": true,
        "AllowStream": true,
        "ContentLanguage": "HIN",
        "Copyright": "2019 Deutsche Grammophon GmbH, Berlin This Compilation ? 2019 Deutsche Grammophon GmbH, Berlin",
        "Translations": [
            {
                "Language": "EN",
                "Value": "Reflections"
            }
        ],
        "ArtistTranslations": []
    },
    "PrimaryReleaseId": 108859186,
    "ReleaseIds": [
        108859186
    ],
    "Translations": [
        {
            "Language": "EN",
            "Value": "Reflections"
        }
    ]
}
    """.trimIndent()

}