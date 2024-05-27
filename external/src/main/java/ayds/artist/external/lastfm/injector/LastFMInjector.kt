package ayds.artist.external.lastfm.injector

import ayds.artist.external.lastfm.data.LastFMAPI
import ayds.artist.external.lastfm.data.LastFMToArtistBiographyResolverImpl
import ayds.artist.external.lastfm.data.LastFMTrackService
import ayds.artist.external.lastfm.data.LastFMTrackServiceImpl
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val LASTFM_BASE_URL = "https://ws.audioscrobbler.com/2.0/"

object LastFMInjector {
    private val lastFMAPI = getLastFMAPI()

    private val lastFMToArtistBiographyResolver = LastFMToArtistBiographyResolverImpl()

    val lastFMTrackService: LastFMTrackService = LastFMTrackServiceImpl(
        lastFMAPI,
        lastFMToArtistBiographyResolver
    )

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(LASTFM_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private fun getLastFMAPI() = getRetrofit().create(LastFMAPI::class.java)
}