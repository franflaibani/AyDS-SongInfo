package ayds.songinfo.moredetails.presentation

import ayds.observer.Subject
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import ayds.songinfo.moredetails.domain.entities.ArtistBiography
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class OtherInfoPresenterTest {

    private val repository: OtherInfoRepository = mockk()
    private val artistBiographyDescriptionHelper:
            ArtistBiographyDescriptionHelper = mockk(relaxUnitFun = true)

    private val otherInfoPresenter: OtherInfoPresenter = OtherInfoPresenterImpl(
        repository, artistBiographyDescriptionHelper
    )

    //¿Is it OK to use artistBiography on subscribe? "IT" DOESN'T WORK.
    @Test
    fun `getArtistInfo should get info and notify the result`(){
        val artistBiography: ArtistBiography = mockk()
        every { repository.getArtistInfo("artistName") } returns artistBiography
        val artistBiographyTester: (ArtistBiography) -> Unit = mockk(relaxed = true)
        otherInfoPresenter.artistBiographyObservable.subscribe {
            artistBiographyTester(artistBiography)
        }

        otherInfoPresenter.getArtistInfo("artistName")

        verify { artistBiographyTester(artistBiography) }
    }
}