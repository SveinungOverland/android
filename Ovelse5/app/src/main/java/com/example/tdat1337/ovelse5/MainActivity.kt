package com.example.tdat1337.ovelse5

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : Activity() {

    val connection by lazy { Connection(this) }

    var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View) {
        gameStarted = false

        findViewById<TextView>(R.id.attemptTextView).visibility = View.INVISIBLE
        findViewById<EditText>(R.id.attemptEditText).visibility = View.INVISIBLE

        findViewById<RelativeLayout>(R.id.layout).visibility = View.VISIBLE
        findViewById<Button>(R.id.sendButton).visibility = View.VISIBLE
    }


    fun sendButtonOnClick(view: View) {
        val params: Map<String, String> =
            when (gameStarted) {
                false -> {
                    gameStarted = true
                    val nameEditText = findViewById<EditText>(R.id.nameEditText)
                    val cardNumberEditText = findViewById<EditText>(R.id.cardNumberEditText)

                    findViewById<TextView>(R.id.attemptTextView).visibility = View.INVISIBLE

                    mapOf(
                        "navn" to nameEditText.text.toString(),
                        "kortnummer" to cardNumberEditText.text.toString()
                    )
                }
                true -> {
                    val numberEditText = findViewById<EditText>(R.id.attemptEditText)

                    mapOf("tall" to numberEditText.text.toString())
                }
            }

        connection.executeHTTPThread(params)
    }


    fun handleResponse(response: String) {
        Log.i("Main activity", response)

        val attemptTextView = findViewById<TextView>(R.id.attemptTextView)
        val attemptEditText = findViewById<EditText>(R.id.attemptEditText)
        val layout = findViewById<RelativeLayout>(R.id.layout)
        val sendButton = findViewById<Button>(R.id.sendButton)

        attemptTextView.text = response

        when {
            response.contains("Oppgi et tall") -> {
                attemptTextView.visibility = View.VISIBLE
                attemptEditText.visibility = View.VISIBLE
                layout.visibility = View.INVISIBLE
            }
            response.contains("du må starte") -> {
                gameStarted = false
                attemptEditText.visibility = View.INVISIBLE
                layout.visibility = View.VISIBLE
            }
            response.contains("du har vunnet") -> {
                sendButton.visibility = View.INVISIBLE
                attemptEditText.visibility = View.INVISIBLE
            }
        }


    }
}
