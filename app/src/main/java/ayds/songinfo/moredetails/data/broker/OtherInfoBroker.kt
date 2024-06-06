package ayds.songinfo.moredetails.data.broker

import ayds.songinfo.moredetails.domain.entities.Card
import java.util.LinkedList

interface OtherInfoBroker {
    fun getArticles(artistName: String): LinkedList<Card>
}

internal class OtherInfoBrokerImpl(
    private val proxyList: LinkedList<OtherInfoProxy>
): OtherInfoBroker {

    override fun getArticles(artistName: String): LinkedList<Card> {
        val articles = LinkedList<Card>()
        proxyList.forEach {
            val card = it.getArticle(artistName)
            card?.let { articles.add(card) }
        }

        return articles
    }
}