package ayds.songinfo.moredetails.data.broker.newyorktimes

import ayds.artist.external.newyorktimes.data.NYT_LOGO_URL
import ayds.artist.external.newyorktimes.data.NYTimesArticle
import ayds.artist.external.newyorktimes.data.NYTimesService
import ayds.songinfo.moredetails.domain.entities.Card

interface NYTimesProxy {
    fun getArticle(artistName: String): Card?
}

internal class NYTimesProxyImpl(private val nyTimesService: NYTimesService): NYTimesProxy {

    override fun getArticle(artistName: String): Card? {
        val nyTimesArticle: NYTimesArticle = nyTimesService.getArtistInfo(artistName)

        return when (nyTimesArticle) {
            is NYTimesArticle.NYTimesArticleWithData ->
                nyTimesArticle.info?.let { Card(artistName, it, nyTimesArticle.url,
                    "NewYorkTimes", NYT_LOGO_URL) }
            is NYTimesArticle.EmptyArtistDataExternal -> null
        }
    }
}