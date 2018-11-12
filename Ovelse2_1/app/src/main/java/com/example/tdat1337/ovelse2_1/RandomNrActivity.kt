package com.example.tdat1337.ovelse2_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import java.util.*

class RandomNrActivity : Activity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val limit = intent.getIntExtra("limit", 100)
            val randNr = Random().nextInt(limit)
            val intent = Intent()

            intent.putExtra("randNr", randNr)
            setResult(RESULT_OK, intent)
            finish()
        }


}