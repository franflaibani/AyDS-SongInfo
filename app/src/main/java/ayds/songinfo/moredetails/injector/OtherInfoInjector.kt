package ayds.songinfo.moredetails.injector

import android.content.Context
import androidx.room.Room
import ayds.artist.external.lastfm.injector.LastFMInjector
import ayds.artist.external.newyorktimes.injector.NYTimesInjector
import ayds.artist.external.wikipedia.injector.WikipediaInjector
import ayds.songinfo.moredetails.data.OtherInfoRepositoryImpl
import ayds.songinfo.moredetails.data.broker.OtherInfoBroker
import ayds.songinfo.moredetails.data.broker.OtherInfoBrokerImpl
import ayds.songinfo.moredetails.data.broker.OtherInfoProxy
import ayds.songinfo.moredetails.data.broker.lastfm.LastFMProxyImpl
import ayds.songinfo.moredetails.data.broker.newyorktimes.NYTimesProxyImpl
import ayds.songinfo.moredetails.data.broker.wikipedia.WikipediaProxyImpl
import ayds.songinfo.moredetails.data.local.CardDatabase
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorageImpl
import ayds.songinfo.moredetails.presentation.CardDescriptionHelperImpl
import ayds.songinfo.moredetails.presentation.OtherInfoPresenter
import ayds.songinfo.moredetails.presentation.OtherInfoPresenterImpl
import java.util.LinkedList

private const val ARTICLE_BD_NAME = "database-article"

object OtherInfoInjector {

    lateinit var presenter: OtherInfoPresenter

    fun initGraph(context: Context) {

        val cardDatabase =
            Room.databaseBuilder(context, CardDatabase::class.java, ARTICLE_BD_NAME).build()

        val articleLocalStorage = OtherInfoLocalStorageImpl(cardDatabase)

        val lastFMProxy = LastFMProxyImpl(LastFMInjector.lastFMTrackService)
        val nyTimesProxy = NYTimesProxyImpl(NYTimesInjector.nyTimesService)
        val wikipediaProxy = WikipediaProxyImpl(WikipediaInjector.wikipediaTrackService)

        val proxyList = LinkedList<OtherInfoProxy>()
        proxyList.addLast(lastFMProxy)
        proxyList.addLast(nyTimesProxy)
        proxyList.addLast(wikipediaProxy)

        val otherInfoBroker: OtherInfoBroker = OtherInfoBrokerImpl(proxyList)

        val repository = OtherInfoRepositoryImpl(articleLocalStorage, otherInfoBroker)

        val artistBiographyDescriptionHelper = CardDescriptionHelperImpl()

        presenter = OtherInfoPresenterImpl(repository, artistBiographyDescriptionHelper)
    }
}