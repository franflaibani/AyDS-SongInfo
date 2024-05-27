package ayds.songinfo.moredetails.data.broker.injector

import ayds.artist.external.lastfm.injector.LastFMInjector
import ayds.artist.external.newyorktimes.injector.NYTimesInjector
import ayds.artist.external.wikipedia.injector.WikipediaInjector
import ayds.songinfo.moredetails.data.broker.lastfm.LastFMProxy
import ayds.songinfo.moredetails.data.broker.lastfm.LastFMProxyImpl
import ayds.songinfo.moredetails.data.broker.newyorktimes.NYTimesProxy
import ayds.songinfo.moredetails.data.broker.newyorktimes.NYTimesProxyImpl
import ayds.songinfo.moredetails.data.broker.wikipedia.WikipediaProxy
import ayds.songinfo.moredetails.data.broker.wikipedia.WikipediaProxyImpl

object OtherInfoBrokerInjector {

    private val lastFMInjector = LastFMInjector
    private val wikipediaInjector = WikipediaInjector
    private val nyTimesInjector = NYTimesInjector

    private val lastFMProxy: LastFMProxy =
        LastFMProxyImpl(lastFMInjector.lastFMTrackService)
    private val wikipediaProxy: WikipediaProxy =
        WikipediaProxyImpl(wikipediaInjector.wikipediaTrackService)
    private val nyTimesProxy: NYTimesProxy =
        NYTimesProxyImpl(nyTimesInjector.nyTimesService)
}