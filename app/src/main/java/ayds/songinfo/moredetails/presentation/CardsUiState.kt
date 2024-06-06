package ayds.songinfo.moredetails.presentation

import java.util.LinkedList

data class CardsUiState(
    val cards: LinkedList<CardUiState>
)
data class CardUiState(
    val artistName: String,
    val html: String,
    val url: String,
    val source: String,
    val articleUrlLogo: String
)