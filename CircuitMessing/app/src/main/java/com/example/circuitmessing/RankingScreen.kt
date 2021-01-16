package com.example.circuitmessing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.products.ProgressManager
import com.example.circuitmessing.ui.auth.LoginActivity
import com.example.circuitmessing.utils.RankingAdapter
import com.example.circuitmessing.utils.SettingsActivity
import com.google.android.material.navigation.NavigationView

class RankingScreen : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking_screen)

        val toolbar: Toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        //val navController = findNavController(R.id.coordinatorLayout)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        // Setting navHeader text to the current user username
        val headerView = navView.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.username) as TextView
        navUsername.text = preferences.username

        val listView: ListView = findViewById(R.id.ranking_list)
        val listItems = arrayOfNulls<Triple<Int, String, Int>>(ProgressManager.rankingList.size)
        for (i in 0 until ProgressManager.rankingList.size) {
            val rank = ProgressManager.rankingList[i]
            listItems[i] = Triple(i+1, rank.username, rank.points)
        }
        for (i in 0 until ProgressManager.rankingList.size) {
            Log.d("List [$i]", listItems[i].toString())
        }
        val adapter = RankingAdapter(this, listItems)
        listView.adapter = adapter
        //listView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        //appBarConfiguration = AppBarConfiguration(setOf(
        //R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_sign_out -> {
                    preferences.isConnected = false
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_forum ->{
                    MainActivity.customTabsIntent.launchUrl(this, Uri.parse(MainActivity.forumUrl))
                }
                R.id.nav_titles -> {
                    val intent = Intent(this, TitleScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_ranking ->{
                    val intent = Intent(this, RankingScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_settings ->{
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                // All other cases for drawer items will go here also
            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
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

    private fun replaceFragment(fragmentId: Int, fragment: Fragment) {
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentId,
            fragment
        )
        fragmentTransaction.commit()
    }
}