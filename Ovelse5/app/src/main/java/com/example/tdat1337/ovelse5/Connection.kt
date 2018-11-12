package com.example.tdat1337.ovelse5

import android.os.AsyncTask
import android.util.Log
import java.io.InputStream
import java.net.*
import java.nio.charset.Charset

class Connection(val activity: MainActivity) {

    init {
        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))
    }

    val ENCODING = "ISO-8859-1"
    val URL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp?"


    fun executeHTTPThread(parameters: Map<String, String>) =
            HttpThread(URL, ENCODING, activity::handleResponse).execute(parameters)



}


class HttpThread(val URL: String, val ENCODING: String, val callback: (String) -> Unit): AsyncTask<Map<String, String>, String, String>() {

    override fun doInBackground(vararg params: Map<String, String>): String {
        return try {
            Log.i("HttpThread", "Sending GET request")
            val url = URL + encodeParameters(params.first(), ENCODING)
            val connection = URL(url).openConnection().apply { setRequestProperty("Accept-Charset", ENCODING) }

            try {
                readResponse(connection.getInputStream(), getCharSet(connection))
            } catch (err: Exception) {
                Log.e("HttpThread", err.message)
                err.message ?: "No error message"
            }
        } catch (err: Exception) {
            Log.e("HttpThread", err.message)
            err.message ?: "No error message"
        }
    }

    override fun onPostExecute(result: String?) { result?.run(callback) }
}


fun encodeParameters(params: Map<String, String>, encoding: String) =
    params.map { "${it.key}=${URLEncoder.encode(it.value, encoding)}" }
        .joinToString(separator = "&")

fun readResponse(inputStream: InputStream, charset: Charset) =
    inputStream.bufferedReader(charset)
        .readLines().joinToString(separator = "")


fun getCharSet(urlConnection: URLConnection): Charset =
    urlConnection.getHeaderField("Content-Type")
        .replace(" ", "").split(";")
        .find { it.startsWith("charset=") }
        .let { charset(it!!.split("=")[1]) }
