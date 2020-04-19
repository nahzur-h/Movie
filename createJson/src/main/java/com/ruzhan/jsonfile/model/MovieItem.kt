package com.ruzhan.jsonfile.model

data class MovieItem(
    var id: String,
    var title: String,
    var type: String,
    var content: String,
    var image: String,
    var logo: String,
    var timestamp: Long,
    var introduceList: ArrayList<IntroduceItem>,
    var videoList: ArrayList<VideoItem>
) {

    companion object {

        @JvmStatic
        fun create() : MovieItem {
            return MovieItem("", "", "",
                "", "", "",
                0, ArrayList(), ArrayList())
        }
    }
}