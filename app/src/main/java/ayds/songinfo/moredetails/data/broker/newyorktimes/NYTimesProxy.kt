package ayds.songinfo.moredetails.data.broker.newyorktimes

import ayds.artist.external.newyorktimes.data.NYT_LOGO_URL
import ayds.artist.external.newyorktimes.data.NYTimesArticle.NYTimesArticleWithData
import ayds.artist.external.newyorktimes.data.NYTimesService
import ayds.songinfo.moredetails.data.broker.OtherInfoProxy
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.entities.CardSource

internal class NYTimesProxyImpl(private val nyTimesService: NYTimesService): OtherInfoProxy {

    override fun getArticle(artistName: String): Card? {
        val nyTimesArticle = nyTimesService.getArtistInfo(artistName)

        if (nyTimesArticle is NYTimesArticleWithData) {
            return nyTimesArticle.toCard()
        }
        return null
    }

    private fun NYTimesArticleWithData.toCard() = Card(name?: "", info?: "", url,
        CardSource.NEW_YORK_TIMES, NYT_LOGO_URL)
}