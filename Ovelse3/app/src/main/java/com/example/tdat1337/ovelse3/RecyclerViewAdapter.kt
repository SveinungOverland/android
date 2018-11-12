package com.example.tdat1337.ovelse3

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

class RecyclerViewAdapter( val mContext: Context, val friends: ArrayList<Friend> ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val TAG = "RecyclerViewAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")

        holder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on: ${friends[position]}")
            Toast.makeText(mContext, "${friends[position]}", Toast.LENGTH_SHORT).show()
        }

        holder.text.text = friends[position].toString()
    }

    override fun getItemCount(): Int = friends.size




    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentLayout: RelativeLayout = itemView.findViewById(R.id.parentlayout)
        var text: TextView = itemView.findViewById(R.id.itemtext)
    }

}