package com.example.circuitmessing.ui.auth
import android.app.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.circuitmessing.R
import com.example.circuitmessing.databinding.ActivityLoginBinding
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.example.circuitmessing.databinding.FragmentRegisterFragmentBinding
import com.example.circuitmessing.fragment_login
import com.example.circuitmessing.fragment_register
import com.example.circuitmessing.ui.classes.User
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginBindingView: ActivityLoginBinding
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        // Bind the LoginView and show it
        loginBindingView = ActivityLoginBinding.inflate(layoutInflater)
        val loginView = loginBindingView.root
        setContentView(loginView)

        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment, fragment_login())
        fragmentTransaction.commit()

        val signUpButton = loginBindingView.signUpButton
        val signInButton = loginBindingView.signInButton


        signUpButton.setOnClickListener(){
            showRegisterFragment()
        }

        signInButton.setOnClickListener {
            showLoginFragment()
        }

    }

    private fun showRegisterFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment, fragment_register())
        fragmentTransaction.commit()
    }

    private fun showLoginFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_and_register_fragment, fragment_login())
        fragmentTransaction.commit()
    }
}

