package com.example.circuitmessing.ui.auth

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.math.BigInteger
import java.security.MessageDigest

class fragment_login : Fragment() {

    companion object {
        fun newInstance() = fragment_login()
    }

    private lateinit var viewModel: FragmentLoginViewModel
    private lateinit var loginBinding: FragmentLoginFragmentBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Instance of the database reference
        database = Firebase.database.reference
        loginBinding = FragmentLoginFragmentBinding.inflate(layoutInflater)
        val view = loginBinding.root

        // Get user data from fragment_login
        var loginUsername = loginBinding.loginUsernameInput.text
        var loginPassword = loginBinding.loginPasswordInput.text
        var loginButton = loginBinding.loginButton

        // Check user login data
        loginButton.setOnClickListener {
            loginUser(loginUsername.toString(), loginPassword.toString())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun loginUser(username: String, password: String){
        var foundUserInDatabase: Boolean = false
        val usersRef = database.child("users").orderByChild("username").equalTo(username)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val dbUsername = ds.child("username").getValue(String::class.java)
                    val dbPassword = ds.child("password").getValue(String::class.java)
                    val dbSalt = ds.child("salt").getValue(String::class.java)

                    val hashedPassword = getSHA512( password + dbSalt.toString())

                    if(dbUsername == username && dbPassword == hashedPassword){
                        foundUserInDatabase = true
                        //A way to change activity
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        activity?.finish()

                        //Once the user connected, we can keep the session to avoid reconnection at each launch
                        // Also used in LoginAcitivity
                        preferences.isConnected = true
                        preferences.username = username
                    }
                    else{
                        loginBinding.loginPasswordInput.error = "Wrong username or password"
                    }
                }
                if(foundUserInDatabase){
                    loginBinding.loginUsernameInput.setText("")
                    loginBinding.loginPasswordInput.setText("")
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Here goes error message
            }
        }
        usersRef.addListenerForSingleValueEvent(valueEventListener)
    }

    private fun getSHA512(input:String):String{
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        val messageDigest = md.digest(input.toByteArray())

        // Convert byte array into signum representation
        val no = BigInteger(1, messageDigest)

        // Convert message digest into hex value
        var hashtext: String = no.toString(16)

        // Add preceding 0s to make it 32 bit
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }

        // return the HashText
        return hashtext
    }

}