package com.ruzhan.jsonfile.model

data class IntroduceItem(
    var type: Int,
    var text: String,
    var image: String
) {
    companion object {
        const val TEXT = 1000
        const val IMAGE = 1001
    }
}