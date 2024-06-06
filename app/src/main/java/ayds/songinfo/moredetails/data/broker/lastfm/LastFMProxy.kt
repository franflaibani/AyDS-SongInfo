package ayds.songinfo.moredetails.data.broker.lastfm

import ayds.artist.external.lastfm.data.LastFMArticle
import ayds.artist.external.lastfm.data.LastFMTrackService
import ayds.songinfo.moredetails.data.broker.OtherInfoProxy
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.entities.CardSource

internal class LastFMProxyImpl(private val lastFMTrackService: LastFMTrackService): OtherInfoProxy {

    override fun getArticle(artistName: String): Card? {
        val lastFMArticle = lastFMTrackService.getArticle(artistName)
        if (lastFMArticle.biography.isNotEmpty()) {
            return lastFMArticle.toCard()
        }
        return null
    }

    private fun LastFMArticle.toCard() =
        Card(artistName, biography, articleUrl, CardSource.LAST_FM, sourceLogoUrl)
}