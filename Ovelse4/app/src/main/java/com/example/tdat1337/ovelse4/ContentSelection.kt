package com.example.tdat1337.ovelse4

import android.app.Activity
import android.app.ListFragment
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView


class ContentSelection: ListFragment() {

    var titles: Array<String>? = null
    var descriptions: Array<String>? = null

    var selectionListener: Selection? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titles = resources.getStringArray(R.array.content_titles)
        descriptions = resources.getStringArray(R.array.content_descriptions)

        listAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, titles!!)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        selectionListener = activity as Selection
    }

    override fun onListItemClick(listView: ListView?, view: View?, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)

        selectionListener?.callback(position, descriptions!![position])
    }
}


interface Selection {
    fun callback(resId: Int, desc: String)
}