package ayds.songinfo.moredetails.presentation

import ayds.songinfo.moredetails.domain.OtherInfoRepository
import ayds.songinfo.moredetails.domain.entities.Card
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class OtherInfoPresenterTest {

    private val otherInfoRepository: OtherInfoRepository = mockk()
    private val artistBiographyDescriptionHelper:
            ArtistBiographyDescriptionHelper = mockk()
    private val otherInfoPresenter: OtherInfoPresenter = OtherInfoPresenterImpl(
        otherInfoRepository, artistBiographyDescriptionHelper
    )

    @Test
    fun `getArtistInfo should return the artist biography ui state`(){
        val card = Card(
            "artistName",
            "biography",
            "articleUrl"
        )
        every { otherInfoRepository.getArtistInfo("artistName") } returns card
        every { artistBiographyDescriptionHelper.getDescription(card) }returns "description"
        val artistBiographyTester: (ArtistBiographyUiState) -> Unit = mockk(relaxed = true)

        otherInfoPresenter.artistBiographyObservable.subscribe {
            artistBiographyTester(it)
        }
        otherInfoPresenter.getArtistInfo("artistName")

        val result = ArtistBiographyUiState(
            "artistName",
            "description",
            "articleUrl"
        )

        verify { artistBiographyTester(result) }
    }
}