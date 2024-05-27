package ayds.songinfo.moredetails.data.local

import ayds.songinfo.moredetails.domain.entities.Card

interface OtherInfoLocalStorage {
    fun getArticle(artistName: String): Card?
    fun insertArtist(card: Card)
}

internal class OtherInfoLocalStorageImpl(
    private val articleDatabase: ArticleDatabase,
) : OtherInfoLocalStorage {

    override fun getArticle(artistName: String): Card? {
        val artistEntity = articleDatabase.ArticleDao().getArticleByArtistName(artistName)
        return artistEntity?.let {
            Card(artistName, artistEntity.biography, artistEntity.articleUrl)
        }
    }

    override fun insertArtist(card: Card) {
        articleDatabase.ArticleDao().insertArticle(
            ArticleEntity(
                card.artistName, card.biography, card.articleUrl
            )
        )
    }
}