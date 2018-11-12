package com.example.tdat1337.project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun changeLang(view: View) {
        startActivityForResult(Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS), 0)
    }

    fun helpButtonOnClick(view: View) {
        startActivity(Intent("com.example.tdat1337.project.HelpActivity"))
    }

    fun newGameButtonOnClick(view: View) {
        startActivity(Intent("com.example.tdat1337.project.GameActivity"))
    }
}
