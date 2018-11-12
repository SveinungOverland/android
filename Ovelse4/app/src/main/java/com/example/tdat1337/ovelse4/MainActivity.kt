package com.example.tdat1337.ovelse4

import android.app.Activity
import android.content.res.TypedArray
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView



class MainActivity : Activity(), Selection {
    var selectedContent = 0

    var contentCovers: TypedArray? = null
    var contentTitles: Array<String>? = null
    var contentDescription: Array<String>? = null

    var coverFragment: ContentFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentCovers = resources.obtainTypedArray(R.array.content_covers)
        contentTitles = resources.getStringArray(R.array.content_titles)
        contentDescription = resources.getStringArray(R.array.content_descriptions)

        coverFragment = fragmentManager.findFragmentById(R.id.cover_fragment) as ContentFragment
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.content_dec -> decSelection().let { true }
            R.id.content_inc -> incSelection().let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun decSelection() { selectedContent--; updateView() }

    fun incSelection() { selectedContent++; updateView() }

    fun updateView() {
        callback(selectedContent, contentDescription!![selectedContent % contentDescription!!.size])

    }

    override fun callback(resId: Int, desc: String) {
        selectedContent = resId
        coverFragment!!.changeImage(contentCovers!!.peekValue(resId % contentCovers!!.length()).resourceId, desc)
    }
}
