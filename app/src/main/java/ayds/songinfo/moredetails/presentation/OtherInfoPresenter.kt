package ayds.songinfo.moredetails.presentation

import ayds.observer.Observable
import ayds.observer.Subject
import ayds.songinfo.moredetails.domain.entities.Card
import ayds.songinfo.moredetails.domain.OtherInfoRepository
import java.util.LinkedList

interface OtherInfoPresenter {
    val artistBiographyObservable: Observable<CardsUiState>
    fun getArtistInfo(artistName: String)

}

internal class OtherInfoPresenterImpl(
    private val repository: OtherInfoRepository,
    private val cardDescriptionHelper: CardDescriptionHelper
) : OtherInfoPresenter {

    override val artistBiographyObservable = Subject<CardsUiState>()

    override fun getArtistInfo(artistName: String) {
        val infoCards = repository.getInfoCards(artistName)

        val uiState = infoCards.toUiState()

        artistBiographyObservable.notify(uiState)
    }

    private fun LinkedList<Card>.toUiState(): CardsUiState {
        val cardsUiState = CardsUiState(LinkedList<CardUiState>())
        this.forEach {
            cardsUiState.cards.addLast(
                CardUiState(
                    it.artistName,
                    cardDescriptionHelper.getDescription(it),
                    it.url,
                    it.source.toString(),
                    it.sourceLogoUrl
                )
            )
        }
        return cardsUiState
    }
}