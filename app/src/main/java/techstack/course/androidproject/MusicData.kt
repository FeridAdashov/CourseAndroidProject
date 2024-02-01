package techstack.course.androidproject

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

enum class MusicType(val type: String, val resType: String) {
    ROCK("rock", "r"), SIX_60("six_60", "s"), JAZZ("jazz", "j")
}

fun String.convertMusicType(): MusicType? = when (this) {
    "r" -> MusicType.ROCK
    "s" -> MusicType.SIX_60
    "j" -> MusicType.JAZZ
    else -> null
}

data class MusicData(
    var author: String,
    var poster: String,
    val viewCount: Int,
    val topCount: String,
    val bio: String,
    val authorLogo: String,
    val musicLogo: String,
    val musicTitle: String,
    val musicType: MusicType?
)


fun mapper(response: MusicResponse): MusicData {
    return MusicData(
        author = response.author ?: "Author",
        poster = response.poster ?: "Poster ${response.author}",
        viewCount = response.viewCount ?: 0,
        topCount = response.author ?: "Author",
        bio = response.author ?: "Author",
        authorLogo = response.authorLogo ?: "Author",
        musicLogo = response.authorLogo ?: "Author",
        musicTitle = response.authorLogo ?: "Author",
        musicType = "j".convertMusicType(),
    )
}


data class MusicResponse(
    var author: String?,
    var poster: String?,
    val viewCount: Int?,
    val topCount: String?,
    val bio: String?,
    val authorLogo: String?,
    val musicLogo: String?,
    val musicTitle: String?,
    val musicType: MusicType?
)

suspend fun getMusicUiData(): MusicData = mapper(getDataFromApi())

private suspend fun getDataFromApi(): MusicResponse = withContext(Dispatchers.IO) {
    delay(3000)

    return@withContext MusicResponse(
        "Oliver Tree",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Faragó%2C_Géza_-_Poster_for_Tungsram_Light_Bulbs_%28ca_1910%29.jpg/220px-Faragó%2C_Géza_-_Poster_for_Tungsram_Light_Bulbs_%28ca_1910%29.jpg",
        24419528,
        "181",
        "An internet based vocalist, producer, writer, director and performance artist, Oliver Tree explores the intersection where pop and alternative meet sonically and has arrived where art and entertainment collide visually. From comedy to action sports, mock reality TV drama to WWF wrestling in his live shows, the world of Oliver Tree is unlike any artist who has come before him.",
        "https://people.com/thmb/LdXGT-Gu8HcT6VHV8TxHyC0V0GQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(704x369:706x371)/Billie-Eilish-3faedce8e5594c63ba28097d35689892.jpg",
        "https://cdn3.vectorstock.com/i/1000x1000/27/57/luxury-music-logo-design-golden-shiny-musical-vector-20332757.jpg",
        "don’t forget your roots - 2021",
        MusicType.JAZZ
    )
}