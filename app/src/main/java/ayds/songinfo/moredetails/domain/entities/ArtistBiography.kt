package ayds.songinfo.moredetails.domain.entities

sealed class Biography {
    data class ArtistBiography(
        val artistName: String,
        val biography: String,
        val articleUrl: String
    ) : Biography() {

    }

    object EmptyBiography : Biography()
}