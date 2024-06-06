package ayds.songinfo.moredetails.data.broker.wikipedia

import ayds.artist.external.wikipedia.data.WikipediaArticle
import ayds.artist.external.wikipedia.data.WikipediaTrackService
import ayds.songinfo.moredetails.data.broker.OtherInfoProxy
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.entities.CardSource

internal class WikipediaProxyImpl(
    private val wikipediaTrackService: WikipediaTrackService)
    : OtherInfoProxy {

    override fun getArticle(artistName: String): Card? {
        val wikipediaArticle: WikipediaArticle? = wikipediaTrackService.getInfo(artistName)

        return wikipediaArticle?.toCard(artistName)
    }

    private fun WikipediaArticle.toCard(artistName: String) = Card (artistName, description,
        wikipediaURL, CardSource.WIKIPEDIA, wikipediaLogoURL)
}