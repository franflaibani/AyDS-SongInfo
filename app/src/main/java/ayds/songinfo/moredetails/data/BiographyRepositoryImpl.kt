package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.external.ExternalService
import ayds.songinfo.moredetails.data.local.LocalStorage
import ayds.songinfo.moredetails.domain.BiographyRepository
import ayds.songinfo.moredetails.fulllogic.ArtistBiography

internal class BiographyRepositoryImpl(
    private val externalService: ExternalService,
    private val localStorage: LocalStorage
) : BiographyRepository {

    override fun getArtistInfoFromRepository(): ArtistBiography {
        TODO("Not yet implemented")
    }

    /*
    private fun getArtistInfoFromRepository(): ArtistBiography {
        val artistName = getArtistName()

        val dbArticle = getArticleFromDB(artistName)

        val artistBiography: ArtistBiography

        if (dbArticle != null) {
            artistBiography = dbArticle.markItAsLocal()
        } else {
            artistBiography = getArticleFromService(artistName)
            if (artistBiography.biography.isNotEmpty()) {
                insertArtistIntoDB(artistBiography)
            }
        }
        return artistBiography
    }

    private fun getArtistName() =
        intent.getStringExtra(ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")
     */
}