package ayds.artist.external.lastfm.data

const val LASTFM_LOGO_URL: String =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Lastfm_logo.svg/320px-Lastfm_logo.svg.png"

data class LastFMArticle(
    val artistName: String,
    val biography: String,
    val articleUrl: String,
)