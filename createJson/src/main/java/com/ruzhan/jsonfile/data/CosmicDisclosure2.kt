package com.ruzhan.jsonfile.data

import com.ruzhan.jsonfile.model.Introduce
import com.ruzhan.jsonfile.model.Movie
import com.ruzhan.jsonfile.model.MovieDetail
import com.ruzhan.jsonfile.model.Video
import java.util.*

object CosmicDisclosure2 {

    private const val ID = 3303
    private const val PLAY_COUNT = "37"

    private const val TITLE = "Cosmic Disclosure"
    private const val TAG = "揭露宇宙"

    private const val IMAGE = "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/cosmi-disclosure02.jpg"

    private const val DESC = "一部关于秘密太空计划（SSP）、宇宙历史、外星人讯息等有关内容的揭露节目。"

    val movie = Movie()
    val movieDetail = MovieDetail()

    private val introduceList = ArrayList<Introduce>()
    private val videoList = ArrayList<Video>()

    init {
        introduceList.add(Introduce(Introduce.TEXT,
                DESC, ""))

        introduceList.add(Introduce(Introduce.IMAGE, "",
                "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/cosmi-disclosure01.jpg"))

        introduceList.add(Introduce(Introduce.TEXT,
                "科里·古德拥有在SSP秘密太空计划（Secret Space Programs） 的“特别行动顾问”职位上服役20多年的经历。", ""))

        introduceList.add(Introduce(Introduce.TEXT,
                "他熟悉外星生物，并且与外星人联合工作、交流多年。关于他不可思议的接触经历，我们可以从节目内容与细节中获知更多首次揭露的信息。", ""))

        videoList.add(Video((ID + 11).toString(),
            "外星生物指南", IMAGE, PLAY_COUNT, 1, "",
            "",
            "https://raw.githubusercontent.com/ruzhan123/Movie/master/home/cosmic-disclosure-corey-guide-to-non-terrestrial-beings/playlist.m3u8"))

        movie.id = ID.toString()
        movie.title = TITLE
        movie.tag = TAG
        movie.desc = DESC
        movie.image = IMAGE

        movieDetail.id = (ID + 3).toString()
        movieDetail.movieId = ID.toString()
        movieDetail.title = TITLE
        movieDetail.tag = TAG
        movieDetail.desc = DESC
        movieDetail.image = IMAGE

        movieDetail.introduces = ArrayList()
        movieDetail.introduces.addAll(introduceList)

        movieDetail.videos = ArrayList()
        movieDetail.videos.addAll(videoList)
    }
}
