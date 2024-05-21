package ayds.songinfo.moredetails.data

import ayds.songinfo.moredetails.data.external.OtherInfoService
import ayds.songinfo.moredetails.data.local.OtherInfoLocalStorage
import ayds.songinfo.moredetails.domain.entities.ArtistBiography
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class OtherInfoRepositoryTest {

    private val otherInfoLocalStorage: OtherInfoLocalStorage = mockk()
    private val otherInfoService: OtherInfoService = mockk()
    private val otherInfoRepository: OtherInfoRepository = OtherInfoRepositoryImpl(
        otherInfoLocalStorage, otherInfoService
    )

    @Test
    fun `getArtistInfo should get article from local storage`() {
        val artistBiography = ArtistBiography(
            "artist",
            "biography",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns artistBiography

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(artistBiography, result)
        Assert.assertTrue(result.isLocallyStored)
    }

    @Test
    fun `getArtistInfo should get article from service`() {
        val artistBiography = ArtistBiography(
            "artist",
            "biography",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns null
        every { otherInfoService.getArticle("artist") } returns artistBiography

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(artistBiography, result)
        Assert.assertTrue(result.isLocallyStored)
    }

    @Test
    fun `on an empty bio, getArtistInfo should get article from service`() {
        val artistBiography = ArtistBiography(
            "artist",
            "",
            "url",
            false
        )
        every { otherInfoLocalStorage.getArticle("artist") } returns null
        every { otherInfoService.getArticle("artist") } returns artistBiography

        val result = otherInfoRepository.getArtistInfo("artist")

        Assert.assertEquals(artistBiography, result)
        Assert.assertFalse(result.isLocallyStored)
        verify(inverse = true) { otherInfoLocalStorage.insertArtist(artistBiography) }
    }
}