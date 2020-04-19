package com.future.movie.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.future.movie.db.entity.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetMovieList(movieList: List<Movie>)

    @Query("SELECT * FROM movie ORDER BY timestamp DESC")
    fun loadMovieList(): Flowable<List<Movie>>

    @Query("SELECT * FROM movie WHERE type=:type ORDER BY timestamp DESC")
    fun loadMovieListTag(type: String): Flowable<List<Movie>>
}