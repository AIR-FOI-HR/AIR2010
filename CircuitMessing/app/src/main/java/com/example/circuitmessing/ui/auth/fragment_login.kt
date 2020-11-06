package com.example.circuitmessing.ui.auth

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

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
        val root = inflater.inflate(R.layout.fragment_login_fragment, container, false)


        println("register fragment")


        loginBinding = FragmentLoginFragmentBinding.inflate(layoutInflater)

        // Get user data from login_main
        var loginUsername = loginBinding.loginUsernameInput.text
        var loginPassword = loginBinding.loginPasswordInput.text
        val loginButton = loginBinding.loginButton
        val i_couldnt_make_the_binding_work = root.findViewById<Button>(R.id.login_button)

        // Check user login data
        i_couldnt_make_the_binding_work.setOnClickListener {
            //loginUser(loginUsername.toString(), loginPassword.toString())

            //A way to change activity
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
            this.activity?.finish()

            //Once the user connected, we can keep the session to avoid reconnection at each launch
            // Also used in LoginAcitivity

            //preferences.isConnected = true
        }

        return root
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