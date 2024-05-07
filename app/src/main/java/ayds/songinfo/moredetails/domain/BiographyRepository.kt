package ayds.songinfo.moredetails.domain

import ayds.songinfo.moredetails.fulllogic.ArtistBiography

interface BiographyRepository {
    fun getArtistInfoFromRepository(): ArtistBiography
}