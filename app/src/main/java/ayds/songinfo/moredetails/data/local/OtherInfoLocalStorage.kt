package ayds.songinfo.moredetails.data.local

import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.entities.CardSource
import java.util.LinkedList

interface OtherInfoLocalStorage {
    fun getCard(artistName: String): Card?
    fun saveCard(card: Card)

    fun getCards(artistName: String): LinkedList<Card>
}

internal class OtherInfoLocalStorageImpl(
    private val cardDatabase: CardDatabase,
) : OtherInfoLocalStorage {

    override fun getCard(artistName: String): Card? {
        val cardEntity = cardDatabase.CardDao().getArticleByArtistName(artistName)
        return cardEntity?.let {
            Card(artistName, cardEntity.biography, cardEntity.articleUrl,
                CardSource.entries[cardEntity.source], cardEntity.sourceLogoUrl)
        }
    }

    override fun saveCard(card: Card) {
        cardDatabase.CardDao().saveCard(
            CardEntity(
                card.artistName, card.text, card.url, card.source.ordinal, card.sourceLogoUrl
            )
        )
    }

    override fun getCards(artistName: String): LinkedList<Card> {
        val list = cardDatabase.CardDao().getCardList(artistName)
        val cardList = LinkedList<Card>()
        list.forEach {
            cardList.addLast(
                Card(
                    it.artistName,
                    it.biography,
                    it.articleUrl,
                    CardSource.entries[it.source],
                    it.sourceLogoUrl
                )
            )
        }
        return cardList
    }
}