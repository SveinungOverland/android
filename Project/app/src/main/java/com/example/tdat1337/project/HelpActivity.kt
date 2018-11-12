package com.example.tdat1337.project

import android.app.Activity
import android.os.Bundle
import android.view.View


class HelpActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_layout)
    }


    fun backButtonOnClick(view: View) {
        finish()
    }

}