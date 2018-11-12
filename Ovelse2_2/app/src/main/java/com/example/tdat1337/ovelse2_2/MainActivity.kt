package com.example.tdat1337.ovelse2_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.function.BinaryOperator

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Items
        val addBtn = findViewById<Button>(R.id.addBtn)
        val multBtn = findViewById<Button>(R.id.multBtn)

        // UI Listeners
        addBtn.setOnClickListener {
            checkAnswer(Arithmetics.PLUS)
        }
        multBtn.setOnClickListener {
            checkAnswer(Arithmetics.TIMES)
        }
    }



    private fun checkAnswer(operation: Arithmetics): Boolean {
        Log.i("Check", "I got here")
        // UI Items
        val nr1 = findViewById<TextView>(R.id.nr1).text.toString().toIntOrNull() ?: 0
        val nr2 = findViewById<TextView>(R.id.nr2).text.toString().toIntOrNull() ?: 0
        val guess = findViewById<EditText>(R.id.answer).text.toString().toIntOrNull() ?: 0

        val answer = operation.apply(nr1, nr2)

        return (answer == guess).also {
            when (it) {
                true -> toast(getString(R.string.correct))
                false -> toast("${getString(R.string.wrong)} $answer")
            }
            dispatchNewIntent()
        }
    }


    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

    private fun dispatchNewIntent() {
        // UI item
        val limit = findViewById<EditText>(R.id.limit).text.toString().toIntOrNull() ?: 0

        val intent = Intent("com.example.tdat1337.RandomNrActivity")
        intent.putExtra("limit", limit)
        startActivityForResult(intent, 1)

        val intent2 = Intent("com.example.tdat1337.RandomNrActivity")
        intent2.putExtra("limit", limit)
        startActivityForResult(intent2, 2)
    }



    enum class Arithmetics: BinaryOperator<Int> {
        PLUS {
            override fun apply(t: Int, u: Int) = t + u
        },
        TIMES {
            override fun apply(t: Int, u: Int) = t * u
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_CANCELED) {

            findViewById<TextView>(
                when (requestCode) {
                    1 -> R.id.nr1
                    else -> R.id.nr2
                })
                .text = data.getIntExtra("randNr", 0).toString()
        }
    }
}
