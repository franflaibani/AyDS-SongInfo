package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.broker.OtherInfoBroker
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import ayds.songinfo.moredetails.domain.entities.CardSource
import java.util.LinkedList

internal class OtherInfoRepositoryImpl(
    private val otherInfoLocalStorage: OtherInfoLocalStorage,
    private val otherInfoBroker: OtherInfoBroker,
) : OtherInfoRepository {

    override fun getInfoCards(artistName: String): LinkedList<Card> {
        val cardList = otherInfoLocalStorage.getCards(artistName)
        val sourceList: LinkedList<Card>

        return if (CardSource.entries.size > cardList.size) {
            sourceList = otherInfoBroker.getArticles(artistName)
            if (sourceList.isNotEmpty()) {
                sourceList.forEach {
                    otherInfoLocalStorage.saveCard(it)
                }
            }
            sourceList
        }else {
            cardList.apply { markItAsLocal() }
        }
    }

    private fun LinkedList<Card>.markItAsLocal() {
        this.forEach { it.isLocallyStored = true }
    }
}