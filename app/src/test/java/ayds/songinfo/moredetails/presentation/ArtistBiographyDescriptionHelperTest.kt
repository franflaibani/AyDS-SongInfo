package ayds.songinfo.moredetails.presentation

import ayds.songinfo.moredetails.domain.entities.ArtistBiography
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistBiographyDescriptionHelperTest {

    private val artistBiographyDescriptionHelper = ArtistBiographyDescriptionHelperImpl()

    //¿How should i manage the expected result?
    @Test
    fun `given a local artist it should return the description` () {
        val artistBiography = ArtistBiography(
            "Plush",
            "biography",
            "url",
            true
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        val expected = "[*]\n" +
                "biography"

        assertEquals(expected, result)
    }

    //¿How should i manage the expected result?
    @Test
    fun `given an no local artist it should return the description` () {
        val artistBiography = ArtistBiography(
            "Plush",
            "biography",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(artistBiography)

        val expected = "biography"

        assertEquals(expected, result)
    }
}