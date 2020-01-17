package com.ruzhan.jsonfile.model

class Video {

    var id: String
    var title: String
    var image: String
    var playCount: String
    var playNumber: Int = 0
    var playNumberDesc: String
    var playWebUrl: String
    var m3u8Url: String = ""

    constructor(id: String, title: String, image: String, playCount: String, playNumber: Int,
                playNumberDesc: String, playWebUrl: String) {
        this.id = id
        this.title = title
        this.image = image
        this.playCount = playCount
        this.playNumber = playNumber
        this.playNumberDesc = playNumberDesc
        this.playWebUrl = playWebUrl
    }

    constructor(id: String, title: String, image: String, playCount: String, playNumber: Int,
                playNumberDesc: String, playWebUrl: String, m3u8Url: String) {
        this.id = id
        this.title = title
        this.image = image
        this.playCount = playCount
        this.playNumber = playNumber
        this.playNumberDesc = playNumberDesc
        this.playWebUrl = playWebUrl
        this.m3u8Url = m3u8Url
    }
}
