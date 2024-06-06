package ayds.songinfo.moredetails.presentation

import ayds.songinfo.moredetails.domain.entities.Card
import org.junit.Assert.assertEquals
import org.junit.Test

class CardDescriptionHelperTest {

    private val artistBiographyDescriptionHelper = CardDescriptionHelperImpl()

    @Test
    fun `given a local artist it should return the biography` () {
        val card = Card(
            "artist",
            "biography",
            "url",
            true
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">[*]biography</font></div></html>",
            result)
    }

    @Test
    fun `given a non local artist it should return the biography` () {
        val card = Card(
            "artist",
            "biography",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography</font></div></html>",
            result)
    }

    @Test
    fun `it should remove apostrophes` () {
        val card = Card(
            "artist",
            "biography'n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography n</font></div></html>",
            result)
    }

    @Test
    fun `it should fix double slashes` () {
        val card = Card(
            "artist",
            "biography\\n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result)
    }

    @Test
    fun `it should fix break lines` () {
        val card = Card(
            "artist",
            "biography\n",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography<br></font></div></html>",
            result)
    }

    @Test
    fun `it should show artist name` () {
        val card = Card(
            "artist",
            "biography artist",
            "url",
            false
        )

        val result = artistBiographyDescriptionHelper.getDescription(card)

        assertEquals(
            "<html><div width=400><font face=\"arial\">biography <b>ARTIST</b></font></div></html>",
            result)
    }
}