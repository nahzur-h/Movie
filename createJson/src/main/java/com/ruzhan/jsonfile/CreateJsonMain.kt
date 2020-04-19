package com.ruzhan.jsonfile

import com.google.gson.Gson
import com.ruzhan.jsonfile.helper.MovieHelper
import com.ruzhan.jsonfile.helper.MovieListFileHelper
import com.ruzhan.jsonfile.model.Movie
import com.ruzhan.jsonfile.model.MovieDetail
import java.io.File

object CreateJsonMain {

    private const val USER_DIR = "user.dir"
    private const val JSON = "json"
    private const val API = "api"
    private const val MOVIE = "movie"
    private const val DETAIL = "detail"
    const val FILE_TYPE = ".json"

    private val mainGSon = Gson()
    private var movieListFile = File("")
    private var movieDetailFile = File("")

    @JvmStatic
    fun main(args: Array<String>) {
//        initCreateJsonFile()
//        createMovieListToJsonFile()
//        createMovieDetailListToJsonFile()
//        println("=======")
//        println("==============")
//        println("======= CreateJsonMain finish !!! =======")
        MovieListFileHelper.init()
    }

    private fun initCreateJsonFile() {
        println("initCreateJsonFile call...")
        val dirFile = File(System.getProperty(USER_DIR))
        val userDirFile = if (dirFile.absolutePath.contains("createJson"))
            dirFile.parentFile else dirFile
        println("userDirFile:${userDirFile.absolutePath}")

        val createJsonFile = File(File(userDirFile, JSON), API)
        println("createJsonFile:${createJsonFile.absolutePath}")

        movieListFile = File(createJsonFile, MOVIE)
        println("movieListFile:${movieListFile.absolutePath}")

        movieDetailFile = File(movieListFile, DETAIL)
        println("movieDetailFile:${movieDetailFile.absolutePath}")
        println("======= initCreateJsonFile finish !!! =========")
    }

    private fun createMovieListToJsonFile() {
        println("createMovieListToJsonFile call...")
        val movieList = getMovieList()
        val movieMap = MovieHelper.getMovieMap(movieList)
        println("createMovieListToJsonFile movieMap size:" + movieMap.size)
        MovieHelper.movieListToJsonFile(movieMap, movieListFile.absolutePath, mainGSon)
        println("======== createMovieListToJsonFile finish !!! ==========")
    }

    private fun getMovieList(): List<Movie> {
        val movieList = MovieHelper.movieList
        for (index in movieList.indices) {
            val item = movieList[index]
            item.id = (index + 1).toString()
        }
        return movieList
    }

    private fun createMovieDetailListToJsonFile() {
        println("createMovieDetailListToJsonFile call...")
        val detailList = getMovieDetailList()
        MovieHelper.movieDetailListToJsonFile(detailList, movieDetailFile.absolutePath, mainGSon)
        println("======= createMovieDetailListToJsonFile finish !!! ==========")
    }

    private fun getMovieDetailList(): List<MovieDetail> {
        val detailList = MovieHelper.movieDetailList
        for (index in detailList.indices) {
            val item = detailList[index]
            item.id = (index + 1 + 10000).toString()
            item.movieId = (index + 1).toString()
        }
        return detailList
    }
}
