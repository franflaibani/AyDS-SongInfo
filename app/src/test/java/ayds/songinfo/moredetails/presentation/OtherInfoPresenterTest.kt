package ayds.songinfo.moredetails.presentation

import ayds.songinfo.moredetails.domain.OtherInfoRepository
import ayds.songinfo.moredetails.domain.entities.Card
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OtherInfoPresenterTest {

    private val otherInfoRepository: OtherInfoRepository = mockk()
    private val cardDescriptionHelper:
            CardDescriptionHelper = mockk()
    private val otherInfoPresenter: OtherInfoPresenter = OtherInfoPresenterImpl(
        otherInfoRepository, cardDescriptionHelper
    )

    @Test
    fun `getArtistInfo should return the artist biography ui state`(){
        val card = Card(
            "artistName",
            "biography",
            "articleUrl"
        )
        every { otherInfoRepository.getInfoCards("artistName") } returns card
        every { cardDescriptionHelper.getDescription(card) }returns "description"
        val artistBiographyTester: (CardUiState) -> Unit = mockk(relaxed = true)

        otherInfoPresenter.artistBiographyObservable.subscribe {
            artistBiographyTester(it)
        }
        otherInfoPresenter.getArtistInfo("artistName")

        val result = CardUiState(
            "artistName",
            "description",
            "articleUrl"
        )

        verify { artistBiographyTester(result) }
    }
}