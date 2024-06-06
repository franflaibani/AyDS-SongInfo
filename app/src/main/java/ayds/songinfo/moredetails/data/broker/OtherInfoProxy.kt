package ayds.songinfo.moredetails.data.broker

import ayds.songinfo.moredetails.domain.entities.Card

interface OtherInfoProxy {
    fun getArticle(artistName: String): Card?
}