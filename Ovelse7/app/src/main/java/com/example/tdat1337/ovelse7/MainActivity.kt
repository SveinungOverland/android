package com.example.tdat1337.ovelse7

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import java.util.ArrayList

import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    private var writers = true

    private val db: DatabaseManager by lazy { DatabaseManager(this) }

    private val dbFile: List<Pair<String, String>> by lazy {
        Log.i("dbFileInit", "Reading database file")

        resources.openRawResource(R.raw.samplefil)
            .bufferedReader()
            .readLines()
            .map { joinedvalue -> joinedvalue.split(";")
                .let { it[0] to it[1] } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.apply {
            clean()

            dbFile.forEach {
                insert(it.first, it.second)
            }

        }

        val res = db.allBooks

        showResults(res)

        //   ArrayList<String> res = db.getAllAuthors();
        //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
        //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
        //   ArrayList<String> res = db.getAllBooksAndAuthors();
    }

    fun showResults(list: ArrayList<String>) {
        val res = StringBuffer("")
        for (s in list) {
            res.append(s + "\n")
        }
        val t = findViewById<TextView>(R.id.tw1)

        t.text = res
    }


    fun toggleTitleWriter(view: View) {
        showResults(if (writers) db.allBooks else db.allAuthors).also { writers = !writers }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.color_settings) {
            startActivityForResult(Intent("com.example.tdat1337.ovelse7.SettingsActivity"), 1)
            true
        } else super.onOptionsItemSelected(item)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val tw1 = findViewById<RelativeLayout>(R.id.layout)
            val backgroundColor = getDefaultSharedPreferences(this).getString("backgroundColorPref", "#ffffff")
            Log.i("ActivityReenter", backgroundColor)
            tw1.setBackgroundColor(Color.parseColor(backgroundColor))
        }
    }
}
