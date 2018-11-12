package com.example.tdat1337.ovelse4

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class ContentFragment: Fragment() {
    var contentDescription: TextView? = null
    var coverView: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.cover_layout, container, false)
        contentDescription = view.findViewById(R.id.content_description)
        coverView = view.findViewById(R.id.content_image)
        return view
    }

    fun changeImage(resId: Int, desc: String) {
        coverView!!.setImageResource(resId)
        contentDescription!!.text = desc
    }
}