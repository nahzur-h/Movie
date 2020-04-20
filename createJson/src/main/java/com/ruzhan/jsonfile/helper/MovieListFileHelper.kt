package com.ruzhan.jsonfile.helper

import com.google.gson.Gson
import com.ruzhan.jsonfile.model.IntroduceItem
import com.ruzhan.jsonfile.model.MovieItem
import com.ruzhan.jsonfile.model.MovieItem2
import com.ruzhan.jsonfile.model.VideoItem
import com.ruzhan.jsonfile.utils.JsonFileIOUtils
import java.io.File
import java.security.MessageDigest
import java.util.regex.Pattern
import javax.xml.bind.DatatypeConverter


object MovieListFileHelper {

    private const val USER_DIR = "user.dir"
    private const val JSON = "json"
    private const val API = "api"
    private const val MOVIE = "movie"
    private const val DETAIL = "detail"
    private const val MOVIE_LIST = "movie-list"
    private const val FILE_TYPE = ".json"

    private val mainGSon = Gson()
    private var userDirFile = File("")
    private var movieListFile = File("")
    private var movieDetailFile = File("")

    @JvmStatic
    fun init() {
        initFile()
        readMovieListFile()
    }

    private fun initFile() {
        println("initCreateJsonFile call...")
        val dirFile = File(System.getProperty(USER_DIR))
        userDirFile = if (dirFile.absolutePath.contains("createJson"))
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

    private fun readMovieListFile() {
        val sourceMovieListFile = File(userDirFile, MOVIE_LIST)
        println("movieListFile:${sourceMovieListFile.absolutePath}")
        if (!sourceMovieListFile.exists()) {
            throw RuntimeException(MOVIE_LIST + "not exists")
        }
        val fileList = sourceMovieListFile.listFiles()
        println("fileList size:${fileList?.size}")
        if (fileList != null) {
            val movieList = getMovieList(fileList)
            if (movieList.isNotEmpty()) {
                createMovieJson(movieList)
                createMovieDetailJson(movieList)
            }
        }
    }

    private fun createMovieJson(movieList: List<MovieItem>) {
        val movieList2 = getMovieItem2List(movieList)
        val filePath = File(File(movieListFile.absolutePath),
            "1".plus(FILE_TYPE)).absolutePath
        val fileJson = mainGSon.toJson(movieList2)
        val isKeyPageSuccess = JsonFileIOUtils.writeFileFromString(filePath, fileJson)
        println("isKeyPageSuccess:$isKeyPageSuccess, filePath:$filePath")
    }

    private fun createMovieDetailJson(movieList: List<MovieItem>) {
        for (movie in movieList) {
            val filePath = File(File(movieDetailFile.absolutePath),
                movie.id.plus(FILE_TYPE)).absolutePath
            val fileJson = mainGSon.toJson(movie)
            val isKeyPageSuccess = JsonFileIOUtils.writeFileFromString(filePath, fileJson)
            println("isKeyPageSuccess:$isKeyPageSuccess, filePath:$filePath")
        }
    }

    private fun getMovieList(fileList: Array<File>): ArrayList<MovieItem> {
        val movieItemList = ArrayList<MovieItem>()
        for (file in fileList) {
            if (file.name.contains("0-sample")) {
                continue
            }
            val movieItem = MovieItem.create()
            movieItem.timestamp = System.currentTimeMillis()
            val readLines = file.readLines()
            if (readLines.isNotEmpty()) {
                var stringBuffer = StringBuffer()
                readLines.forEach { line ->
                    when (line) {
                        "<title>",
                        "<type>",
                        "<image>",
                        "<content>",
                        "<introduce-text>",
                        "<introduce-image>",
                        "<introduce-video-title>",
                        "<introduce-video-image>",
                        "<introduce-video-m3u8>",
                        "" -> {
                            stringBuffer = StringBuffer()
                        }
                        "</title>" -> {
                            val title = stringBuffer.toString()
                            movieItem.id = md5(title)
                            movieItem.title = title
                        }
                        "</type>" -> {
                            movieItem.type = stringBuffer.toString()
                        }
                        "</image>" -> {
                            movieItem.image = stringBuffer.toString()
                        }
                        "</content>" -> {
                            val content = stringBuffer.toString()
                            movieItem.content = content
                        }
                        "</introduce-text>" -> {
                            val introduceList = movieItem.introduceList
                            val introduceItem = IntroduceItem(IntroduceItem.TEXT,
                                stringBuffer.toString(), "")
                            introduceList.add(introduceItem)
                        }
                        "</introduce-image>" -> {
                            val introduceList = movieItem.introduceList
                            val introduceItem = IntroduceItem(IntroduceItem.IMAGE,
                                "", stringBuffer.toString())
                            introduceList.add(introduceItem)
                        }
                        "</introduce-video-title>" -> {
                            val videoList = movieItem.videoList
                            val videoItem = VideoItem("", "", "")
                            videoItem.title = stringBuffer.toString()
                            videoList.add(videoItem)
                        }
                        "</introduce-video-image>" -> {
                            val videoList = movieItem.videoList
                            val videoItem = videoList.last()
                            videoItem.image = stringBuffer.toString()
                        }
                        "</introduce-video-m3u8>" -> {
                            val videoList = movieItem.videoList
                            val videoItem = videoList.last()
                            videoItem.m3u8Url = stringBuffer.toString()
                        }
                        else -> {
                            val resultLine = replaceBlank(line)
                            if (resultLine.isNotBlank()) {
                                stringBuffer.append(line)
                            }
                        }
                    }
                }
            }
            movieItemList.add(movieItem)
        }
        return movieItemList
    }


    private fun replaceBlank(str: String?): String {
        var resultText = ""
        if (str != null) {
            val pattern = Pattern.compile("\\s*|\t|\r|\n")
            val matcher = pattern.matcher(str);
            resultText = matcher.replaceAll("");
        }
        return resultText;
    }

    private fun md5(input: String?): String {
        var result = ""
        if (input != null) {
            val md = MessageDigest.getInstance("MD5")
            md.update(input.toByteArray())
            val digest: ByteArray = md.digest()
            result = DatatypeConverter.printHexBinary(digest).toUpperCase()
        }
        return result
    }

    private fun getMovieItem2List(list: List<MovieItem>): ArrayList<MovieItem2> {
        val newList = ArrayList<MovieItem2>()
        for (item in list) {
            newList.add(MovieItem2(item.id, item.title, item.type,
                item.content, item.image, item.logo, item.timestamp))
        }
        return newList
    }
}