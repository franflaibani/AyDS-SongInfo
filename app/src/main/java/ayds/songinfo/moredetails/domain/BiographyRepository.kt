package ayds.songinfo.moredetails.domain

import ayds.songinfo.moredetails.domain.entities.Biography

interface BiographyRepository {
    fun getArtistInfoFromRepository(artistName: String): Biography
}