package tj.androidtvtestapp.model

object SampleSportList {

    val list: List<Sport> by lazy {
        setupSports()
    }
    private var count: Long = 0

    private fun setupSports(): List<Sport> {
        val title = arrayOf(
                "UEFA Champions League 19/20 Coming Soon ",
                "Atletico M vs FC Liverpool Highlights",
                "Leipzig vs Tottenham Best Highlights",
                "Manchester City vs Dinamo Zg. Best Moments&Scores")

        val description = "The competition has been won by 22 clubs, 12 of which have won it more than once." +
                " Real Madrid is the most successful club in the tournament's history," +
                " having won it 13 times, including its first five seasons. Liverpool are the reigning champions, " +
                "having beaten Tottenham Hotspur 2–0 in the 2019 final. Spanish clubs have the highest number of victories (18 wins), " +
                "followed by England (13 wins) and Italy (12 wins). England has the largest number of winning teams, with five clubs having won the title."

        val studio = arrayOf(
                "Sport News",
                "Sport News",
                "Sport News",
                "Sport News")
        val videoUrl = arrayOf(
                "",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
                "",
                "")
        val bgImageUrl = arrayOf(
                "https://i.ytimg.com/vi/IBK2E1StGhc/maxresdefault.jpg",
                "https://i.ytimg.com/vi/yFKQUlYnwY0/maxresdefault.jpg",
                "https://i.ytimg.com/vi/LGO4JYqc0tc/maxresdefault.jpg",
                "https://i.ytimg.com/vi/mlnqpdoJ7SM/maxresdefault.jpg")
        val cardImageUrl = arrayOf(
            "https://i.ytimg.com/vi/IBK2E1StGhc/maxresdefault.jpg",
            "https://i.ytimg.com/vi/yFKQUlYnwY0/maxresdefault.jpg",
            "https://i.ytimg.com/vi/LGO4JYqc0tc/maxresdefault.jpg",
            "https://i.ytimg.com/vi/mlnqpdoJ7SM/maxresdefault.jpg")

        return title.indices.map {
            buildSportInfo(
                title[it],
                description,
                studio[it],
                videoUrl[it],
                cardImageUrl[it],
                bgImageUrl[it]
            )
        }
    }

    private fun buildSportInfo(
            title: String,
            description: String,
            studio: String,
            videoUrl: String,
            cardImageUrl: String,
            backgroundImageUrl: String): Sport {
        val movie = Sport()
        movie.id = count++
        movie.title = title
        movie.description = description
        movie.studio = studio
        movie.cardImageUrl = cardImageUrl
        movie.backgroundImageUrl = backgroundImageUrl
        movie.videoUrl = videoUrl
        return movie
    }
}
