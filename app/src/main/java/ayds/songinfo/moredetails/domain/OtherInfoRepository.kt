package ayds.songinfo.moredetails.domain

import ayds.songinfo.moredetails.domain.entities.Card
import java.util.LinkedList

interface OtherInfoRepository {
    fun getInfoCards(artistName: String): LinkedList<Card>
}