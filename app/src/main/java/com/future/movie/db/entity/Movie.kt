package com.future.movie.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie (
    @PrimaryKey
    var id: String,
    var type: String,
    var title: String,
    var content: String,
    var image: String,
    var logo: String,
    var timestamp: Long
)