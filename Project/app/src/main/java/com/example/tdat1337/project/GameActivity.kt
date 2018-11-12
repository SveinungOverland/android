package com.example.tdat1337.project

import android.app.Activity
import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*
import kotlin.properties.Delegates

class GameActivity: Activity() {



    val nrCorrect: TextView by lazy { findViewById<TextView>(R.id.nrCorrect) }
    val nrWrong: TextView by lazy { findViewById<TextView>(R.id.nrWrong) }
    val nrLeft: TextView by lazy { findViewById<TextView>(R.id.nrLeft) }

    val statusSuccess: TextView by lazy { findViewById<TextView>(R.id.statusSuccess) }
    val statusFailure: TextView by lazy { findViewById<TextView>(R.id.statusFailure) }

    val wordProgress: TextView by lazy { findViewById<TextView>(R.id.wordProgress) }
    val imageProgress: ImageView by lazy { findViewById<ImageView>(R.id.imageProgress) }


    var correct: Int by Delegates.observable(0) { _, _, newValue ->
        nrCorrect.text = newValue.toString()
    }
    var wrong: Int by Delegates.observable(0) { _, _, newValue ->
        nrWrong.text = newValue.toString()
    }
    var left: Int by Delegates.observable(0) { _, _, newValue ->
        nrLeft.text = newValue.toString()
    }
    var step: Int by Delegates.observable(0) { _, _, newValue ->
        imageProgress.setImageResource(images.getResourceId(newValue, 0))
        updateWordProgress()
    }

    val pressedButtons = mutableListOf<Int>()

    val guessedLetters = mutableListOf<Char>()


    val images: TypedArray by lazy { resources.obtainTypedArray(R.array.images) }

    val wordStack: Stack<String> by lazy {
        Stack<String>().also { stack ->
            resources.openRawResource(R.raw.words)
                .bufferedReader()
                .readLines()
                .map(String::toUpperCase)
                .shuffled()
                .forEach { stack.push(it) }
        }.also { left = it.size }
    }

    var waitingState: Int by Delegates.observable(0) {_, _, newValue ->
        when (newValue) {
            0 -> { statusFailure.visibility = View.INVISIBLE; statusSuccess.visibility = View.INVISIBLE }
            1 -> { statusSuccess.visibility = View.VISIBLE; }
            2 -> { statusFailure.visibility = View.VISIBLE; }
        }
    }


    var word: String = ""
        set(value) { step = 0; field = value }
        get() {
            return field.map { if (guessedLetters.contains(it)) it else '_' }.joinToString("")
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        wordStack
        images


        step = 0
        left = wordStack.size
        word = wordStack.peek()
        updateWordProgress()
    }




    fun buttonOnClick(view: View) {

        if (waitingState != 0) {
            waitingState = 0
            newWord()
            return
        }

        view as TextView
        val viewChar = view.text[0]

        pressedButtons.add(view.id)
        guessedLetters.add(viewChar)

        view.visibility = View.INVISIBLE


        when {
            step == 10 -> {
                // User has no more tries and has failed
                revealWord()
                waitingState = 2
                wrong++
            }
            wordStack.peek().contains(viewChar) -> {
                // User has guessed correct letter
                updateWordProgress()

                if (!word.contains('_')) {
                    waitingState = 1
                    correct++
                }
            }
            else -> {
                // User has guessed the wrong letter
                step++
            }
        }
    }


    fun updateWordProgress() {
        wordProgress.text = word
    }


    fun revealWord() {
        wordProgress.text = wordStack.peek()
    }


    fun newWord() {
        wordStack.pop()
        resetButtons()
        word = wordStack.peek()
        left--
    }

    fun resetButtons() = pressedButtons
        .forEach { findViewById<View>(it).visibility = View.VISIBLE }
        .also { guessedLetters.clear() }
}