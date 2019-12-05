package com.ruzhan.jsonfile.data

import com.ruzhan.jsonfile.model.Introduce
import com.ruzhan.jsonfile.model.Movie
import com.ruzhan.jsonfile.model.MovieDetail
import com.ruzhan.jsonfile.model.Video
import java.util.*

object DaftPunk {

    private const val ID = 3903
    private const val PLAY_COUNT = "4"

    private const val TITLE = "Daft Punk Unchained"
    private const val TAG = "音乐"

    private const val IMAGE = "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/daft-punk.jpg"

    private const val DESC = "Daft Punk，他们用开放的音乐态度，打破浩室、迪斯科和放克音乐的传统分野，开发变化多端的旋律、声响与歌唱，创造出幽默而不失深度，极富想象力的作品。"

    val movie = Movie()
    val movieDetail = MovieDetail()

    private val introduceList = ArrayList<Introduce>()
    private val videoList = ArrayList<Video>()

    init {
        introduceList.add(Introduce(Introduce.TEXT,
                DESC, ""))

        introduceList.add(Introduce(Introduce.IMAGE, "",
                "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/daft-punk01.jpg"))

        introduceList.add(Introduce(Introduce.TEXT,
                "以一首「Get Lucky」红遍全球的法国电音天团「傻瓜庞克」，成军20年来却极少公开露面，尔后更彻底以机器人形象现身活动。", ""))

        introduceList.add(Introduce(Introduce.IMAGE, "",
                "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/daft-punk02.jpg"))

        introduceList.add(Introduce(Introduce.TEXT,
                "法国导演赫威马丁戴皮耶的纪录片《傻瓜庞克：谁是傻瓜》，邀请到肯伊威斯特、「菲董」菲瑞威廉斯等大牌巨星，", ""))

        introduceList.add(Introduce(Introduce.TEXT,
                "横跨法英美三国的音乐製作人、乐评及音乐录影带导演现身说法，探索傻瓜庞克的创作历程，如何将向来由英美领导的流行音乐带向新纪元。", ""))

        introduceList.add(Introduce(Introduce.IMAGE, "",
                "https://raw.githubusercontent.com/ruzhan123/Lion/master/json/api/image/daft-punk03.jpg"))

        introduceList.add(Introduce(Introduce.TEXT,
                "傻朋克著名于他们的表演皆经过精心的策划，并将视觉的效果及元素融入进音乐中、作品亦皆有如故事般的铺陈。", ""))

        introduceList.add(Introduce(Introduce.TEXT,
                "自2001年以来，傻瓜朋克大多以机器人的扮相出现在公众视野，而他们的头盔以及手套就是其机器人扮相中很重要的标志。", ""))

        introduceList.add(Introduce(Introduce.TEXT,
                "两人为了保持乐团的神秘感，基本都不会接受采访或是在电视节目里出现。", ""))

        videoList.add(Video((ID + 11).toString(),
                "【纪录片】被解放的蠢朋克 【2015】【法国】", IMAGE, PLAY_COUNT, 1, "电影",
                "http://player.bilibili.com/player.html?aid=5623215&cid=9133202&page=1"))

        videoList.add(Video((ID + 12).toString(),
                "【Daft Punk】Alive2007，法国的电音天堂", IMAGE, PLAY_COUNT, 2, "电影",
                "http://player.bilibili.com/player.html?aid=2795311&cid=4366499&page=1"))

        videoList.add(Video((ID + 13).toString(),
                "音乐分享 你听懂Daft Punk - Instant Crush了吗", IMAGE, PLAY_COUNT, 3, "音乐",
                "http://player.bilibili.com/player.html?aid=27614298&page=1"))

        videoList.add(Video((ID + 14).toString(),
                "Random Access Memories", IMAGE, PLAY_COUNT, 4, "音乐",
                "http://player.bilibili.com/player.html?aid=2285300&cid=3562854&page=1"))

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
