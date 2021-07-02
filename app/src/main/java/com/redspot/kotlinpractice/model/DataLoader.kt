package com.redspot.kotlinpractice.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.redspot.kotlinpractice.BuildConfig
import com.redspot.kotlinpractice.model.rest_entities.MoviesCategoryDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val URL_BASE = "https://api.themoviedb.org/3/movie/"
const val CATEGORY_POPULAR = "popular"
const val CATEGORY_UPCOMING = "upcoming"
const val CATEGORY_TOP_RATED = "top_rated"
const val API = "?api_key=" + BuildConfig.TMDB_API_KEY
const val DATA_LANG = "&language=en-US"
const val GET_REQUEST = "GET"

object DataLoader {
    fun loadCategory(category: String): MoviesCategoryDTO? {
        try {
            val uri = URL(URL_BASE + category + API + DATA_LANG)

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = GET_REQUEST
                urlConnection.readTimeout = 10000
                when (urlConnection.responseCode) {
                    200 -> {
                        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                        val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                            getLinesOld(bufferedReader)
                        } else {
                            getLines(bufferedReader)
                        }
                        return Gson().fromJson(lines, MoviesCategoryDTO::class.java)
                    }
                    else -> throw Exception(urlConnection.responseCode.toString() + ": " + urlConnection.responseMessage)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                throw Exception(e.message)
            }
            finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw Exception("MalformedURLException")
        }
    }

    private fun getLinesOld(reader: BufferedReader) : String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }

        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader) : String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}