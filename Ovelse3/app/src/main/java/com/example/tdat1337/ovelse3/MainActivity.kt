package com.example.tdat1337.ovelse3

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    val friends = ArrayList<Friend>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        friends.add(Friend("Navn Navnesen", "1.01.0001"))
        friends.add(Friend("Kåre Willock", "3.14.1337"))

        initRecycler()

        fab.setOnClickListener { view ->
            handleNewFriend(view)
        }
    }

    private fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.listWrapper)
        val adapter = RecyclerViewAdapter(this, friends)

        recyclerView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun handleNewFriend(view: View) {
        Snackbar.make(view, "Whoops, this function has not been implemented yet", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}

class Friend ( var name: String, var birthdate: String ) {
    override fun toString(): String = "$name ($birthdate)"
}