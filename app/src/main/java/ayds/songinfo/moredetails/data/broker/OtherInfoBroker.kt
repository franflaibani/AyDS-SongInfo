package ayds.songinfo.moredetails.data.broker

import ayds.songinfo.moredetails.data.broker.lastfm.LastFMProxy
import ayds.songinfo.moredetails.data.broker.newyorktimes.NYTimesProxy
import ayds.songinfo.moredetails.data.broker.wikipedia.WikipediaProxy
import ayds.songinfo.moredetails.domain.entities.Card
import java.util.LinkedList

interface OtherInfoBroker {
    fun getArticles(artistName: String): LinkedList<Card>
}

internal class OtherInfoBrokerImpl(
    private val lastFMProxy: LastFMProxy,
    private val nyTimesProxy: NYTimesProxy,
    private val wikipediaProxy: WikipediaProxy
): OtherInfoBroker {

    override fun getArticles(artistName: String): LinkedList<Card> {
        val lastFMArticle = lastFMProxy.getArticle(artistName)
        val nyTimesArticle = nyTimesProxy.getArticle(artistName)
        val wikipediaArticle = wikipediaProxy.getArticle(artistName)

        val articles = LinkedList<Card>()
        articles.addLast(lastFMArticle)
        articles.addLast(nyTimesArticle)
        articles.addLast(wikipediaArticle)

        return articles
    }
}