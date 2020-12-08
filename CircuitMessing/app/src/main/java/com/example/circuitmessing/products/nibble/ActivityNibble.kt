package com.example.circuitmessing.products.nibble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.ProgressManager.Companion.checkDonePages
import com.example.circuitmessing.products.ProgressManager.Companion.updatePageDone
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_ringo.*

class ActivityNibble : AppCompatActivity() {
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nibble)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
                    replaceFragment(R.id.nibble_fragment, NibbleIntroductionFragment())
                }
                R.id.nav_meet_tools -> {
                    replaceFragment(R.id.nibble_fragment, NibbleMeeetTheToolsFragment())
                }
                R.id.nav_time_makin -> {
                    replaceFragment(R.id.nibble_fragment, NibbleTimeToGetMakinFragment())
                }
                R.id.nav_summed_up -> {
                    replaceFragment(R.id.nibble_fragment, NibbleFinishingUpFragment())
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

        replaceFragment(R.id.nibble_fragment, NibbleIntroductionFragment())

        // Update database when specific page is done
        rightArrow?.setOnClickListener {
            when {
                navView.menu.getItem(1).isChecked -> {
                    val item = navView.menu.getItem(1)
                    updatePageDone(productName = "nibble", pageName = "intro", item)
                    replaceFragment(R.id.nibble_fragment, NibbleMeeetTheToolsFragment())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(2).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "nibble", pageName = "tools", item)
                    replaceFragment(R.id.nibble_fragment, NibbleTimeToGetMakinFragment())
                    navView.menu.getItem(3).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(3)
                    updatePageDone(productName = "nibble", pageName = "makin", item)
                    replaceFragment(R.id.nibble_fragment, NibbleFinishingUpFragment())
                    navView.menu.getItem(4).isChecked = true;
                }
                navView.menu.getItem(4).isChecked -> {
                    val item = navView.menu.getItem(4)
                    updatePageDone(productName = "nibble", pageName = "summed", item)
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
                    replaceFragment(R.id.nibble_fragment, NibbleIntroductionFragment())
                    navView.menu.getItem(1).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "nibble", pageName = "makin", item)
                    replaceFragment(R.id.nibble_fragment, NibbleMeeetTheToolsFragment())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(4).isChecked -> {
                    val item = navView.menu.getItem(3)
                    updatePageDone(productName = "nibble", pageName = "summed", item)
                    replaceFragment(R.id.nibble_fragment, NibbleTimeToGetMakinFragment())
                    navView.menu.getItem(3).isChecked = true;
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

    private fun replaceFragment(fragmentId: Int,fragment: Fragment) {
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentId,
            fragment
        )
        fragmentTransaction.commit()
    }
}


