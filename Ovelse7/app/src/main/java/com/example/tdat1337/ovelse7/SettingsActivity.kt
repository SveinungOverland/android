package com.example.tdat1337.ovelse7

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("SettingsActivity", "Creating activity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)
    }

    fun backButtonOnClick(view: View) {
        Log.i("SettingsActivity", "Back button pressed")
        setResult(RESULT_OK, Intent().apply { putExtra("Result", 1) })
        finish()
    }
}