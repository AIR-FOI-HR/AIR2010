package com.example.circuitmessing.utils

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.RankingScreen
import com.example.circuitmessing.TitleScreen
import com.example.circuitmessing.ui.auth.LoginActivity
import com.google.android.material.navigation.NavigationView
import java.util.*

class SettingsActivity : AppCompatActivity() {
    lateinit var locale: Locale
    private var currentLanguage = "en"
    private var currentLang: String? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        currentLanguage = resources.configuration.locale.toString()


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
                R.id.nav_forum -> {
                    MainActivity.customTabsIntent.launchUrl(this, Uri.parse(MainActivity.forumUrl))
                }
                R.id.nav_titles -> {
                    val intent = Intent(this, TitleScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_ranking -> {
                    val intent = Intent(this, RankingScreen::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_settings -> {
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

        val langswitch: CompoundButton = findViewById(R.id.language_switch)
        Log.d("CurrentLang: ", currentLanguage)
        langswitch.isChecked = currentLanguage != "en_US" && currentLanguage != "en" && currentLanguage != "en_us"
        langswitch.setOnCheckedChangeListener { _, isChecked ->
            if (langswitch.isChecked) {
                Log.d("LangSwitch: ", "ON")
                setLocale("hr")
            }
            else {
                Log.d("LangSwitch: ", "OFF")
                setLocale("en")
            }
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

    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(
                this,
                MainActivity::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
        }
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