package ayds.songinfo.moredetails.data.local

import ayds.songinfo.moredetails.domain.entities.Biography

interface LocalStorage {
    fun getArticleFromDB(artistName: String): Biography.ArtistBiography?

    fun insertArtistIntoDB(artistBiography: Biography.ArtistBiography)
}

internal class LocalStorageImpl(
    articleDatabase: ArticleDatabase
): LocalStorage {
    private val articleDao: ArticleDao = articleDatabase.ArticleDao()

    override fun getArticleFromDB(artistName: String): Biography.ArtistBiography? {
        val artistEntity = articleDao.getArticleByArtistName(artistName)
        return artistEntity?.let {
            Biography.ArtistBiography(artistName, artistEntity.biography, artistEntity.articleUrl)
        }
    }

    override fun insertArtistIntoDB(artistBiography: Biography.ArtistBiography) {
        articleDao.insertArticle(
            ArticleEntity(
                artistBiography.artistName, artistBiography.biography, artistBiography.articleUrl
            )
        )
    }
}