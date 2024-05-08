package ayds.songinfo.moredetails.presentation

private fun getArtistName() =
    intent.getStringExtra(ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")