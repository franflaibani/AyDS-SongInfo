package ayds.artist.external.lastfm.data

import java.io.IOException

interface LastFMTrackService {
    fun getArticle(artistName: String): LastFMArticle?
}
internal class LastFMTrackServiceImpl(
    private val lastFMAPI: LastFMAPI,
    private val lastFMToArtistBiographyResolver: LastFMToArtistBiographyResolver
) : LastFMTrackService {

    override fun getArticle(artistName: String): LastFMArticle? {

        var artistBiography: LastFMArticle? = null
        try {
            val callResponse = getSongFromService(artistName)
            artistBiography = lastFMToArtistBiographyResolver.map(callResponse.body(), artistName)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return artistBiography
    }

    private fun getSongFromService(artistName: String) =
        lastFMAPI.getArtistInfo(artistName).execute()

}