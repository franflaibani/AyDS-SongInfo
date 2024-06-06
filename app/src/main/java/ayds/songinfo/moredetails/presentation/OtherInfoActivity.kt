package ayds.songinfo.moredetails.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ayds.songinfo.R
import ayds.songinfo.moredetails.injector.OtherInfoInjector
import com.squareup.picasso.Picasso

class OtherInfoActivity : Activity() {
    private lateinit var cardTextView1: TextView
    private lateinit var openUrlButton1: Button
    private lateinit var logoImageView1: ImageView

    private lateinit var cardTextView2: TextView
    private lateinit var openUrlButton2: Button
    private lateinit var logoImageView2: ImageView

    private lateinit var cardTextView3: TextView
    private lateinit var openUrlButton3: Button
    private lateinit var logoImageView3: ImageView

    private lateinit var presenter: OtherInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)

        initViewProperties()
        initPresenter()

        observePresenter()
        getArtistInfoAsync()
    }

    private fun initPresenter() {
        OtherInfoInjector.initGraph(this)
        presenter = OtherInfoInjector.presenter
    }

    private fun observePresenter() {
        presenter.artistBiographyObservable.subscribe { artistBiography ->
            updateUi(artistBiography)
        }
    }

    private fun initViewProperties() {
        cardTextView1 = findViewById(R.id.textPane1)
        openUrlButton1 = findViewById(R.id.openUrlButton1)
        logoImageView1 = findViewById(R.id.logoImageView1)

        cardTextView2 = findViewById(R.id.textPane2)
        openUrlButton2 = findViewById(R.id.openUrlButton2)
        logoImageView2 = findViewById(R.id.logoImageView2)

        cardTextView3 = findViewById(R.id.textPane3)
        openUrlButton3 = findViewById(R.id.openUrlButton3)
        logoImageView3 = findViewById(R.id.logoImageView3)
    }

    private fun getArtistInfoAsync() {
        Thread {
            getArtistCard()
        }.start()
    }

    private fun getArtistCard() {
        val artistName = getArtistName()
        presenter.getArtistInfo(artistName)
    }

    private fun updateUi(uiState: CardsUiState) {
        runOnUiThread {
            uiState.cards.getOrNull(0)?.let { updateCard1(it) }
            uiState.cards.getOrNull(1)?.let { updateCard2(it) }
            uiState.cards.getOrNull(2)?.let { updateCard3(it) }
        }
    }

    private fun updateCard1(card: CardUiState) {
        updateOpenUrlButton(openUrlButton1, card.url)
        updateImageLogo(logoImageView1, card.articleUrlLogo)
        updateCardText(cardTextView1, card.html)
    }

    private fun updateCard2(card: CardUiState) {
        updateOpenUrlButton(openUrlButton2, card.url)
        updateImageLogo(logoImageView2, card.articleUrlLogo)
        updateCardText(cardTextView2, card.html)
    }

    private fun updateCard3(card: CardUiState) {
        updateOpenUrlButton(openUrlButton3, card.url)
        updateImageLogo(logoImageView3, card.articleUrlLogo)
        updateCardText(cardTextView3, card.html)
    }

    private fun updateOpenUrlButton(openUrlButton: Button, url: String) {
        openUrlButton.setOnClickListener {
            navigateToUrl(url)
        }
    }

    private fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun updateImageLogo(sourceImageView: ImageView, url: String) {
        Picasso.get().load(url).into(sourceImageView)
    }

    private fun getArtistName() =
        intent.getStringExtra(ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")

    private fun updateCardText(cardContentTextView: TextView, infoHtml: String) {
        cardContentTextView.text = Html.fromHtml(infoHtml)
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}