package ayds.songinfo.moredetails.data

import ayds.artist.external.lastfm.external.OtherInfoService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class OtherInfoRepositoryTest {

    private val otherInfoLocalStorage: OtherInfoLocalStorage = mockk()
    private val otherInfoService: ayds.artist.external.lastfm.external.OtherInfoService = mockk()
    private val otherInfoRepository: OtherInfoRepository = OtherInfoRepositoryImpl(
        otherInfoLocalStorage, otherInfoService
    )

    @Test
    fun `getArtistInfo should get article from local storage`() {
        val card = Card(
            "artist",
            "biography",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns card

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(card, result)
        Assert.assertTrue(result.isLocallyStored)
    }

    @Test
    fun `getArtistInfo should get article from service`() {
        val card = Card(
            "artist",
            "biography",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns null
        every { otherInfoService.getArticle("artist") } returns card

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(card, result)
        Assert.assertTrue(result.isLocallyStored)
    }

    @Test
    fun `on an empty bio, getArtistInfo should get article from service`() {
        val card = Card(
            "artist",
            "",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns null
        every { otherInfoService.getArticle("artist") } returns card

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(card, result)
        Assert.assertFalse(result.isLocallyStored)
        verify(inverse = true) { otherInfoLocalStorage.insertArtist(card) }
    }
}