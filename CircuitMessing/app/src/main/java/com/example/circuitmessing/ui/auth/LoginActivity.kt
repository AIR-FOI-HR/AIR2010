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
import com.example.circuitmessing.databinding.LoginMainBinding
import com.example.circuitmessing.fragment_login
import com.example.circuitmessing.fragment_register
import com.example.circuitmessing.ui.classes.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : FragmentActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var database: DatabaseReference
    private lateinit var registerBinding: FragmentRegisterFragmentBinding
    private lateinit var loginBinding: FragmentLoginFragmentBinding
    private lateinit var loginBindingView: ActivityLoginBinding

    private var mFragmentManager: FragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
         */

        // Bind the LoginView and show it
        loginBindingView = ActivityLoginBinding.inflate(layoutInflater)
        val loginView = loginBindingView.root
        setContentView(loginView)

        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_fragment, fragment_login())
        fragmentTransaction.commit()


        // Instance of the database reference
        database = Firebase.database.reference


        //val fragment =  loginBindingView.navRegisterFragment
        val toggleButton = loginBindingView.signUpButton
        toggleButton.setOnClickListener(){
            showRegisterFragment()
        }

    }

    private fun showRegisterFragment(){
        registerBinding = FragmentRegisterFragmentBinding.inflate(layoutInflater)
        val view = registerBinding.root

        val fragmentTransaction : FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_fragment, fragment_register())
        fragmentTransaction.commit()

        // Get user data from register_main
        var username = registerBinding.registerUsernameInput.text
        var password = registerBinding.registerPasswordInput.text
        var repeatPassword = registerBinding.registerPasswordInputRepeat.text
        var registerButton = registerBinding.registerButton

        // Save new user to the database on a click of a register button
        registerButton.setOnClickListener {
            var user = createNewUser(username.toString(), password.toString())
            if(password.toString() == repeatPassword.toString()) {
                registerUser(username.toString(), user)
            }
            else {
                // Warning message about passwords don't match goes here
            }
        }
    }

    private fun showLoginFragment(){
        loginBinding = FragmentLoginFragmentBinding.inflate(layoutInflater)

        // Get user data from login_main
        var loginUsername = loginBinding.loginUsernameInput.text
        var loginPassword = loginBinding.loginPasswordInput.text
        var loginButton = loginBinding.loginButton

        // Check user login data
        loginButton.setOnClickListener {
            loginUser(loginUsername.toString(), loginPassword.toString())
        }
    }

    private fun createNewUser(username: String, password: String): User {
        return User(username = username, password = password, points = 0)
    }

    private fun registerUser(username: String, user: User){
        var foundUserInDatabase: Boolean = false
        val usersRef = database.child("users").orderByChild("username").equalTo(username)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val dbUsername = ds.child("username").getValue(String::class.java)
                    if(dbUsername == username){
                        foundUserInDatabase = true
                    }
                }
                if(!foundUserInDatabase){
                    // User successfully created
                    database.child("users").child(username).setValue(user)
                }
                else {
                    // Message: "User with that username already exists!"
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Here goes error message
            }
        }
        usersRef.addListenerForSingleValueEvent(valueEventListener)
    }

    private fun loginUser(username: String, password: String){
        var foundUserInDatabase: Boolean = false
        val usersRef = database.child("users").orderByChild("username").equalTo(username)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val dbUsername = ds.child("username").getValue(String::class.java)
                    val dbPassword = ds.child("password").getValue(String::class.java)
                    if(dbUsername == username && dbPassword == password){
                        foundUserInDatabase = true
                    }
                    else{
                        // Message error about wrong credentials
                    }
                }
                if(!foundUserInDatabase){
                    // Grant user access

                }
                else {
                    // Message: "User with that username already exists!"
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Here goes error message
            }
        }
        usersRef.addListenerForSingleValueEvent(valueEventListener)
    }
}

