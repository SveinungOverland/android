package com.example.tdat1337.ovelse2_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Items
        val randnrBtn = findViewById<Button>(R.id.randnrBtn)


        // Listeners
        randnrBtn.setOnClickListener {
            val intent = Intent("com.example.tdat1337.RandomNrActivity")
            intent.putExtra("limit", 100)
            startActivityForResult(intent, 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Tilfelding tall ${data.getIntExtra("randNr", 1)}", Toast.LENGTH_SHORT).show()
        }
    }


}
