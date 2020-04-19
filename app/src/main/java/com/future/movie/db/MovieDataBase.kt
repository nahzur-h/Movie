package com.future.movie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.future.movie.db.dao.MovieDao
import com.future.movie.db.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "DB_MOVIE"
        @Volatile
        private var INSTANCE : MovieDataBase? = null

        fun get(context : Context): MovieDataBase {
            return INSTANCE ?: synchronized(MovieDataBase::class) {
                INSTANCE ?: buildDataBase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDataBase(context: Context): MovieDataBase {
            return Room.databaseBuilder(context,
                MovieDataBase::class.java, DB_NAME).build()
        }
    }

    abstract fun movieDao(): MovieDao
}