package com.example.circuitmessing.ui.auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.circuitmessing.databinding.ActivityLoginBinding.inflate
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.example.circuitmessing.databinding.FragmentRegisterFragmentBinding
import com.example.circuitmessing.ui.classes.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register_fragment.view.*
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom

class fragment_register : Fragment() {

    companion object {
        fun newInstance() =
            fragment_register()
    }

    private lateinit var database: DatabaseReference
    private lateinit var viewModel: FragmentRegisterViewModel
    private lateinit var registerBinding: FragmentRegisterFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Instance of the database reference
        database = Firebase.database.reference
        registerBinding = FragmentRegisterFragmentBinding.inflate(layoutInflater)
        val view = registerBinding.root

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
                registerBinding.passwordField2.error = "Passwords don't match!"
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentRegisterViewModel::class.java)
        // TODO: Use the ViewModel

    }

    private fun createNewUser(username: String, password: String): User {
        val salt = generateSalt().toString()
        val hashedPassword = getSHA512(password + salt)
        return User(username = username, password = hashedPassword, salt = salt, points = 0)
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
                    registerBinding.registerUsernameInput.setText("")
                    registerBinding.registerPasswordInput.setText("")
                    registerBinding.registerPasswordInputRepeat.setText("")
                    registerBinding.usernameField.error = null
                    registerBinding.passwordField2.error = null
                    database.child("users").child(username).setValue(user)
                }
                else {
                    registerBinding.usernameField.error = "User with that username already exists, try again."
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Here goes error message
            }
        }
        usersRef.addListenerForSingleValueEvent(valueEventListener)
    }

    private fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)

        return salt
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