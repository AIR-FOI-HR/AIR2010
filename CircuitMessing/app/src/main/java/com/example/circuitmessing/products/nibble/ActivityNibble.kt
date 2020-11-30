package com.example.circuitmessing.products.nibble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.makerbuino.Makerbuino_introduction
import com.example.circuitmessing.products.makerbuino.Makerbuino_meet_the_tools
import com.example.circuitmessing.products.makerbuino.Makerbuino_time_to_get_makin
import com.example.circuitmessing.products.ringo.Ringo_time_to_get_makin_fragment
import com.example.circuitmessing.products.ringo.Ringo_introduction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ringo.*
import kotlinx.android.synthetic.main.nibble_time_to_get_makin_fragment.*

class ActivityNibble : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nibble)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        database = Firebase.database.reference

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // Setting navHeader text to the current user username
        val headerView = navView.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.username) as TextView
        navUsername.text = preferences.username

        // Highlight introduction on the start
        navView.menu.getItem(1).isChecked = true;

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    returnHome()
                }
                R.id.nav_introduction -> {
                    replaceFragment(R.id.nibble_fragment, Nibble_introduction_fragment())
                }
                R.id.nav_meet_tools -> {
                    replaceFragment(R.id.nibble_fragment, Nibble_meet_the_tools())
                }
                R.id.nav_time_makin -> {
                    replaceFragment(R.id.nibble_fragment, Nibble_time_to_get_makin())
                }
                R.id.nav_summed_up -> {

                }
                R.id.nav_quiz -> {

                }
            }

            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }

        // Check which pages are already done
        val item1 = navView.menu.getItem(1)
        val item2 = navView.menu.getItem(2)
        val item3 = navView.menu.getItem(3)
        val item4 = navView.menu.getItem(4)

        checkDonePages(productName = "nibble", pageName = "intro", item1)
        checkDonePages(productName = "nibble", pageName = "tools", item2)
        checkDonePages(productName = "nibble", pageName = "makin", item3)
        checkDonePages(productName = "nibble", pageName = "summed", item4)

        replaceFragment(R.id.nibble_fragment, Nibble_introduction_fragment())

        // Update database when specific page is done
        rightArrow?.setOnClickListener {
            when {
                navView.menu.getItem(1).isChecked -> {
                    val item = navView.menu.getItem(1)
                    updatePageDone(productName = "nibble", pageName = "intro", item)
                    replaceFragment(R.id.nibble_fragment, Nibble_meet_the_tools())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(2).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "nibble", pageName = "tools", item)
                    replaceFragment(R.id.nibble_fragment, Nibble_time_to_get_makin())
                    navView.menu.getItem(3).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(3)
                    updatePageDone(productName = "nibble", pageName = "makin", item)
                }
                navView.menu.getItem(4).isChecked -> {
                    // summed
                }
                navView.menu.getItem(4).isChecked -> {
                    // quiz
                }
            }
        }
        leftArrow?.setOnClickListener {
            when {
                navView.menu.getItem(1).isChecked -> {
                    returnHome()
                }
                navView.menu.getItem(2).isChecked -> {
                    val item = navView.menu.getItem(1)
                    updatePageDone(productName = "nibble", pageName = "tools", item)
                    replaceFragment(R.id.nibble_fragment, Nibble_introduction_fragment())
                    navView.menu.getItem(1).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "nibble", pageName = "makin", item)
                    replaceFragment(R.id.nibble_fragment, Nibble_meet_the_tools())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(4).isChecked -> {
                    // summed
                }
                navView.menu.getItem(5).isChecked -> {
                    // quiz
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        returnHome()
    }

    private fun returnHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updatePageDone(productName: String, pageName: String, item: MenuItem) {
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
                    item.setIcon(R.drawable.ic_baseline_check_24)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Here goes error message
            }
        }
        pageRef.addListenerForSingleValueEvent(valueEventListener)
    }

    private fun checkDonePages(productName: String, pageName: String, item: MenuItem) {
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

    private fun replaceFragment(fragmentId: Int,fragment: Fragment) {
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentId,
            fragment
        )
        fragmentTransaction.commit()
    }
}


