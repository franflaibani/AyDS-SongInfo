package ayds.songinfo.moredetails.domain.entities

data class Card(
    val artistName: String,
    val biography: String,
    val articleUrl: String,
    val source: String,
    val sourceLogoUrl: String,
    var isLocallyStored: Boolean = false
)