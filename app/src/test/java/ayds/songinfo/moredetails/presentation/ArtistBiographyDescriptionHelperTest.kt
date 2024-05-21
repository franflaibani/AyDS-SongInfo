package ayds.songinfo.moredetails.presentation

import ayds.songinfo.moredetails.domain.entities.ArtistBiography
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistBiographyDescriptionHelperTest {

    private val artistBiographyDescriptionHelper = ArtistBiographyDescriptionHelperImpl()

    @Test
    fun `given a local artist it should return the biography` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography",
            "url",
            true
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">[*]biography</font></div></html>",
            result)
    }

    @Test
    fun `given a non local artist it should return the biography` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography</font></div></html>",
            result)
    }

    @Test
    fun `it should remove apostrophes` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography'n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography n</font></div></html>",
            result)
    }

    @Test
    fun `it should fix double slashes` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography\\n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result)
    }

    @Test
    fun `it should fix break lines` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography\n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result)
    }

    @Test
    fun `it should show artist name` () {
        val artistBiography = ArtistBiography(
            "artist",
            "biography artist",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography <b>ARTIST</b></font></div></html>",
            result)
    }
}