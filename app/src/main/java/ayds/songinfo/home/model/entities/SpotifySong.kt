package ayds.songinfo.home.model.entities

sealed class Song {
    data class SpotifySong(
        val id: String,
        val songName: String,
        val artistName: String,
        val albumName: String,
        val releaseDate: String,
        val spotifyUrl: String,
        val imageUrl: String,
        val releaseDatePrecision: String,
        var isLocallyStored: Boolean = false
    ) : Song() {

        val precisedDate = getPrecisionDate(releaseDate, releaseDatePrecision)

        private fun getPrecisionDate(releaseDate: String, releaseDatePrecision: String): String {
            when (releaseDatePrecision) {
                "day" -> {return getDayDate(releaseDate)}
                "month" -> {return getMonthDate(releaseDate)}
                "year" -> {return getYearDate(releaseDate)}
            }
            return "date not found"
        }

        private fun getDayDate(releaseDate: String): String {
            val date = releaseDate.split("-")
            val day = date[2]
            val month = date[1]
            val year = date[0]
            return "$day/$month/$year"
        }

        private fun getYearDate(releaseDate: String): String {
            val year = releaseDate.toInt()
            return when (leapYear(year)) {
                true -> "$year (a leap year)"
                false -> "$year (not a leap year)"
            }
        }

        private fun leapYear(year: Int): Boolean {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
        }

        private fun getMonthDate(releaseDate: String): String {
            val year = releaseDate.split("-").first()
            val month = getMonthName(releaseDate.split("-").last())
            return "$month, $year"
        }

        private fun getMonthName(month: String): String {
            return when (month){
                "01" -> "January"
                "02" -> "February"
                "03" -> "March"
                "04" -> "April"
                "05" -> "May"
                "06" -> "June"
                "07" -> "July"
                "08" -> "August"
                "09" -> "September"
                "10" -> "October"
                "11" -> "November"
                else -> "December"
            }
        }
    }

    object EmptySong : Song()
}

