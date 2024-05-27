package ayds.songinfo.moredetails.data

import ayds.artist.external.lastfm.data.LastFMTrackService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.OtherInfoRepository

internal class OtherInfoRepositoryImpl(
    private val otherInfoLocalStorage: OtherInfoLocalStorage,
    private val lastFMTrackService: LastFMTrackService,
) : OtherInfoRepository {

    override fun getArtistInfo(artistName: String): Card {
        val dbArticle = otherInfoLocalStorage.getArticle(artistName)

        val card: Card

        if (dbArticle != null) {
            card = dbArticle.apply { markItAsLocal() }
        } else {
            card = lastFMTrackService.getArticle(artistName)
            if (card.biography.isNotEmpty()) {
                otherInfoLocalStorage.insertArtist(card)
            }
        }
        return card
    }

    private fun Card.markItAsLocal() {
        isLocallyStored = true
    }
}