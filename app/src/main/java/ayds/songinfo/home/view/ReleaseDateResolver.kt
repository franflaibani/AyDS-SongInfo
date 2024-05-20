package ayds.songinfo.home.view

import ayds.songinfo.home.model.entities.Song

interface ReleaseDateResolverFactory {
    fun getReleaseDateResolver(song: Song.SpotifySong): ReleaseDateResolver
}

internal class ReleaseDateResolverFactoryImpl : ReleaseDateResolverFactory {
    override fun getReleaseDateResolver(song: Song.SpotifySong): ReleaseDateResolver =
        when (song.releaseDatePrecision) {
            "day" -> ReleaseDateDayResolver(song)
            "month" -> ReleaseDateMonthResolver(song)
            "year" -> ReleaseDateYearResolver(song)
            else -> ReleaseDateDefaultResolver(song)
        }
}

interface ReleaseDateResolver {
    val song: Song.SpotifySong
    fun getReleaseDate(): String
}

internal class ReleaseDateDayResolver(override val song: Song.SpotifySong) : ReleaseDateResolver {
    override fun getReleaseDate(): String {
        val date = song.releaseDate.split("-")
        val year = date[0]
        val month = date[1]
        val day = date[2]
        return "$day/$month/$year"
    }
}

internal class ReleaseDateMonthResolver(override val song: Song.SpotifySong) : ReleaseDateResolver {
    override fun getReleaseDate(): String {
        val date = song.releaseDate.split("-")
        val year = date[0]
        val month = date[1]
        return "${month.toInt().toMonthString()}, $year"
    }

    private fun Int.toMonthString() =
        when (this) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> ""
        }
}

internal class ReleaseDateYearResolver(override val song: Song.SpotifySong) : ReleaseDateResolver {
    override fun getReleaseDate(): String {
        val isLeapYear = isLeapYear(song.releaseDate.toInt())
        val suffix = if (isLeapYear) "(leap year)" else "(not a leap year)"
        return "${song.releaseDate} $suffix"
    }

    private fun isLeapYear(year: Int) = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

internal class ReleaseDateDefaultResolver(override val song: Song.SpotifySong) : ReleaseDateResolver {
    override fun getReleaseDate() = song.releaseDate
}