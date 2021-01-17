package com.example.circuitmessing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.browser.customtabs.CustomTabsService
import androidx.fragment.app.FragmentManager
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.databinding.NavHeaderRingoBinding
import com.example.circuitmessing.products.ProgressManager
import com.example.circuitmessing.products.ProgressManager.Companion.getAllTitles
import com.example.circuitmessing.products.ProgressManager.Companion.getRanking
import com.example.circuitmessing.products.quiz.classes.Quiz
import com.example.circuitmessing.ui.auth.LoginActivity
import com.example.circuitmessing.utils.SettingsActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Setting navHeader text to the current user username
        val headerView = navView.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.username) as TextView
        navUsername.text = preferences.username
        setTheme(preferences.nightMode)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_sign_out -> {
                    preferences.isConnected = false
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_forum ->{
                      customTabsIntent.launchUrl(this, Uri.parse(forumUrl))
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

        getAllTitles()
        getRanking()
        // FETCH ALL QUESTIONS. Needs to be like this to call suspended function
        GlobalScope.launch {
            GetAllQuestions()
        }
    }

    private fun setTheme(nightMode: Boolean) {
        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        // Question objects for all products. Each has a list of questions that need fetching
        var ringoQuiz = Quiz("Ringo")
        var nibbleQuiz = Quiz("Nibble")
        var makerbuinoQuiz = Quiz("Makerbuino")

        // Function for fetching all questions. Calls FetchQuestions method that gets all the questions from database
        suspend fun GetAllQuestions() {
            ringoQuiz.FetchQuestions()
            nibbleQuiz.FetchQuestions()
            makerbuinoQuiz.FetchQuestions()
        }
      
        // WebView setup
        val forumUrl = "https://community.circuitmess.com/"
        val builder = Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}

