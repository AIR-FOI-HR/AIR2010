package com.example.circuitmessing.products.ringo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.example.circuitmessing.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.ProgressManager.Companion.checkDonePages
import com.example.circuitmessing.products.ProgressManager.Companion.giveUserTitle
import com.example.circuitmessing.products.ProgressManager.Companion.updatePageDone
import com.example.circuitmessing.products.quiz.QuizActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_ringo.*


class ActivityRingo : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mFragmentManager: FragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ringo)

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

        // Set listener on the drawer to check which option is selected and then do something
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    returnHome()
                }
                R.id.nav_introduction -> {
                    replaceFragment(R.id.ringo_fragment, RingoIntroductionFragment())
                }
                R.id.nav_meet_tools -> {
                    replaceFragment(R.id.ringo_fragment, RingoMeetTheToolsFragment())
                }
                R.id.nav_time_makin -> {
                    replaceFragment(R.id.ringo_fragment, RingoTimeToGetMakinFragmentFragment())
                }
                R.id.nav_summed_up -> {
                    replaceFragment(R.id.ringo_fragment, RingoFinishingUpFragment())
                }
                R.id.nav_quiz -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    var product : Bundle = Bundle()
                    product.putString("product", "ringo"); //Your id
                    intent.putExtras(product) //Put your id to your next Intent
                    startActivity(intent)
                    finish()
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

        checkDonePages(productName = "ringo", pageName = "intro", item1)
        checkDonePages(productName = "ringo", pageName = "tools", item2)
        checkDonePages(productName = "ringo", pageName = "makin", item3)
        checkDonePages(productName = "ringo", pageName = "summed", item4)

        replaceFragment(R.id.ringo_fragment, RingoIntroductionFragment())

        // Update database when specific page is done
        rightArrow?.setOnClickListener {
            when {
                navView.menu.getItem(1).isChecked -> {
                    val item = navView.menu.getItem(1)
                    updatePageDone(productName = "ringo", pageName = "intro", item)
                    replaceFragment(R.id.ringo_fragment, RingoMeetTheToolsFragment())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(2).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "ringo", pageName = "tools", item)
                    giveUserTitle(username = preferences.username, titleName = "ringoNovice")
                    replaceFragment(R.id.ringo_fragment, RingoTimeToGetMakinFragmentFragment())
                    navView.menu.getItem(3).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(3)
                    updatePageDone(productName = "ringo", pageName = "makin", item)
                    giveUserTitle(username = preferences.username, titleName = "ringoArhitect")
                    replaceFragment(R.id.ringo_fragment, RingoFinishingUpFragment())
                    navView.menu.getItem(4).isChecked = true;
                }
                navView.menu.getItem(4).isChecked -> {
                    val item = navView.menu.getItem(4)
                    updatePageDone(productName = "ringo", pageName = "summed", item)
                    giveUserTitle(username = preferences.username, titleName = "ringoMaster")
                    val intent = Intent(this, QuizActivity::class.java)
                    var product : Bundle = Bundle()
                    product.putString("product", "ringo"); //Your id
                    intent.putExtras(product) //Put your id to your next Intent
                    startActivity(intent)
                    finish()
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
                    updatePageDone(productName = "ringo", pageName = "tools", item)
                    replaceFragment(R.id.ringo_fragment, RingoIntroductionFragment())
                    navView.menu.getItem(1).isChecked = true;
                }
                navView.menu.getItem(3).isChecked -> {
                    val item = navView.menu.getItem(2)
                    updatePageDone(productName = "ringo", pageName = "makin", item)
                    replaceFragment(R.id.ringo_fragment, RingoMeetTheToolsFragment())
                    navView.menu.getItem(2).isChecked = true;
                }
                navView.menu.getItem(4).isChecked -> {
                    val item = navView.menu.getItem(3)
                    updatePageDone(productName = "ringo", pageName = "summed", item)
                    replaceFragment(R.id.ringo_fragment, RingoTimeToGetMakinFragmentFragment())
                    navView.menu.getItem(3).isChecked = true;
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

    private fun replaceFragment(fragmentId: Int, fragment: Fragment) {
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentId,
            fragment
        )
        fragmentTransaction.commit()
    }
}

