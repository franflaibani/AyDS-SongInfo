package ayds.songinfo.moredetails.domain

import ayds.songinfo.moredetails.domain.entities.ArtistBiography

interface OtherInfoRepository {
    fun getArtistInfo(artistName: String): ArtistBiography
}