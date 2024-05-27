package ayds.songinfo.moredetails.data.broker.wikipedia

import ayds.artist.external.wikipedia.data.WikipediaArticle
import ayds.artist.external.wikipedia.data.WikipediaTrackService
import ayds.songinfo.moredetails.domain.entities.Card

interface WikipediaProxy {
    fun getArticle(artistName: String): Card?
}

internal class WikipediaProxyImpl(
    private val wikipediaTrackService: WikipediaTrackService)
    : WikipediaProxy {

    override fun getArticle(artistName: String): Card? {
        val wikipediaArticle: WikipediaArticle? = wikipediaTrackService.getInfo(artistName)

        return wikipediaArticle?.let {
            Card(artistName,
                wikipediaArticle.description,
                wikipediaArticle.wikipediaURL,
                "Wikipedia",
                wikipediaArticle.wikipediaLogoURL)
        }
    }
}