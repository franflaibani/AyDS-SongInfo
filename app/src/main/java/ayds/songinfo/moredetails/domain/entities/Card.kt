package ayds.songinfo.moredetails.domain.entities

data class Card(
    val artistName: String,
    val text: String,
    val url: String,
    val source: CardSource,
    val sourceLogoUrl: String,
    var isLocallyStored: Boolean = false
)

enum class CardSource{
    LAST_FM,
    NEW_YORK_TIMES,
    WIKIPEDIA
}