package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.external.ExternalService
import ayds.songinfo.moredetails.data.local.LocalStorage
import ayds.songinfo.moredetails.domain.BiographyRepository
import ayds.songinfo.moredetails.domain.entities.Biography

internal class BiographyRepositoryImpl(
    private val externalService: ExternalService,
    private val localStorage: LocalStorage
) : BiographyRepository {

    override fun getArtistInfoFromRepository(artistName: String): Biography {
        val dbArticle = localStorage.getArticleFromDB(artistName)
        val artistBiography: Biography

        if (dbArticle != null) {
            artistBiography = dbArticle.markItAsLocal()
        } else {
            artistBiography = externalService.getArticleFromService(artistName)
            if (artistBiography.biography.isNotEmpty()) {
                localStorage.insertArtistIntoDB(artistBiography)
            }
        }
        return artistBiography
    }

    private fun Biography.ArtistBiography.markItAsLocal() = copy(biography = "[*]$biography")
}