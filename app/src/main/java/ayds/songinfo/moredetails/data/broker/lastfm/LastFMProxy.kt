package ayds.songinfo.moredetails.data.broker.lastfm

import ayds.artist.external.lastfm.data.LASTFM_LOGO_URL
import ayds.artist.external.lastfm.data.LastFMArticle
import ayds.artist.external.lastfm.data.LastFMTrackService
import ayds.songinfo.moredetails.domain.entities.Card

interface LastFMProxy {
    fun getArticle(artistName: String): Card?
}

internal class LastFMProxyImpl(private val lastFMTrackService: LastFMTrackService): LastFMProxy {

    override fun getArticle(artistName: String): Card? {
        val lastFMArticle: LastFMArticle? = lastFMTrackService.getArticle(artistName)
        return lastFMArticle?.let {
            Card(artistName,
                lastFMArticle.biography,
                lastFMArticle.articleUrl,
                "LastFM",
                LASTFM_LOGO_URL)
        }
    }
}