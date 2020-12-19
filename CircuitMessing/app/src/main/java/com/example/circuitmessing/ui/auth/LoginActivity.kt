package com.example.circuitmessing.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.databinding.ActivityLoginBinding
//import com.example.circuitmessing.fragment_register

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginBindingView: ActivityLoginBinding
    private var mFragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)


        //If the session is keep to avoid the need to reconnect user each launch
        if (preferences.isConnected){
            //A way to change activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // Bind the LoginView and show it
        loginBindingView = ActivityLoginBinding.inflate(layoutInflater)
        val loginView = loginBindingView.root
        setContentView(loginView)

        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.login_and_register_fragment,
            FragmentLogin()
        )
        fragmentTransaction.commit()

        val signUpButton = loginBindingView.signUpButton
        val signInButton = loginBindingView.signInButton
        val toggleGroup = loginBindingView.loginToggleButton
        toggleGroup.check(signInButton.id)

        signUpButton.setOnClickListener(){
            showRegisterFragment()
        }

        signInButton.setOnClickListener {
            showLoginFragment()
        }

    }

    private fun showRegisterFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.login_and_register_fragment,
            FragmentRegister()
        )
        fragmentTransaction.commit()
    }

    private fun showLoginFragment(){
        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.login_and_register_fragment,
            FragmentLogin()
        )
        fragmentTransaction.commit()
    }
}

