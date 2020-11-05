package com.example.circuitmessing

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
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
import com.example.circuitmessing.databinding.LoginMainBinding
import com.example.circuitmessing.databinding.RegisterMainBinding
import com.example.circuitmessing.ui.classes.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var database: DatabaseReference
    private lateinit var registerBinding: RegisterMainBinding
    private lateinit var loginBinding: LoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
      
        // Instance of the database reference
        database = Firebase.database.reference
      
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Bind the RegisterMain and set it to View
        registerBinding = RegisterMainBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        // Get user data from register_main
        var username = registerBinding.usernameFieldInput.text
        var password = registerBinding.passwordFieldInput.text
        var repeatPassword = registerBinding.passwordFieldInput.text
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

        // Bind the login_main and set it to View
        loginBinding = LoginMainBinding.inflate(layoutInflater)
        val loginView = loginBinding.root
        setContentView(loginView)

        // Get user data from login_main
        var loginUsername = loginBinding.usernameFieldInput.text
        var loginPassword = loginBinding.passwordFieldInput.text
        var loginButton = loginBinding.loginButton

        // Check user login data
        loginButton.setOnClickListener {
            loginUser(loginUsername.toString(), loginPassword.toString())
        }


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

