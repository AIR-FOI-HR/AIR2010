package com.example.circuitmessing.products

import android.util.Log
import android.view.MenuItem
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.R
import com.example.circuitmessing.ui.classes.User
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class ProgressManager {

    companion object {
        private val database = Firebase.database.reference

        public var titles: MutableList<String> = arrayListOf()

        fun getAllTitles() {
            titles.clear()
            val pageRef = database.child("titles")
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (title in dataSnapshot.children) {
                        //Log.d("TITLE: ", titles.toString())
                        for (user in title.children) {
                            //Log.d("USER: ", users.toString())
                            if (user.key == preferences.username && user.value == true){
                                //Log.d("ADDED TITLE: ", titles.key.toString())
                                titles.add(title.key.toString())
                            }
                        }
                    }
                    //Log.d("ALL TITLES: ", Titles.toString())
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }

        fun updatePageDone(productName: String, pageName: String, item: MenuItem) {
            var finishedPage: Boolean = false
            val pageRef = database.child(productName).child(pageName)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val dbUsername = ds.key
                        if (dbUsername == preferences.username) {
                            finishedPage = true
                        }
                    }
                    if (!finishedPage) {
                        // Page completed
                        database.child(productName).child(pageName).child(preferences.username)
                            .setValue(true)
                        database.child("users").child(preferences.username).child("points").setValue(ServerValue.increment(100))
                        item.setIcon(R.drawable.ic_baseline_check_24)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }

        fun checkDonePages(productName: String, pageName: String, item: MenuItem) {
            val pageRef = database.child(productName).child(pageName)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        if (ds.key == preferences.username && ds.value == true) {
                            item.setIcon(R.drawable.ic_baseline_check_24)
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }

        fun giveUserTitle(username: String, titleName: String){
            var finishedPage: Boolean = false
            val pageRef = database.child("titles").child(titleName)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val dbUsername = ds.key
                        if (dbUsername == preferences.username) {
                            finishedPage = true
                        }
                    }
                    if (!finishedPage) {
                        // Page completed
                        pageRef.child(username).setValue(true)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }
    }

}