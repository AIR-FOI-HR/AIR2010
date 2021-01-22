package com.example.circuitmessing.utils

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.circuitmessing.R

class RankingAdapter(private val context: Context, private val arrayList: Array<Triple<Int, String, Int>?>) : BaseAdapter() {
    private lateinit var rankingPlace: TextView
    private lateinit var username: TextView
    private lateinit var points: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item_rank, parent, false)
        rankingPlace = convertView.findViewById(R.id.ranking_place)
        username = convertView.findViewById(R.id.ranking_username)
        points = convertView.findViewById(R.id.ranking_score)
        rankingPlace.text = arrayList[position]?.first.toString()
        username.text = arrayList[position]?.second
        points.text = arrayList[position]?.third.toString()
        if (arrayList[position]?.second == preferences.username) {
            rankingPlace.setTextColor(Color.parseColor("#F1592A"))
            username.setTextColor(Color.parseColor("#F1592A"))
            points.setTextColor(Color.parseColor("#F1592A"))
        }
        return convertView
    }
}