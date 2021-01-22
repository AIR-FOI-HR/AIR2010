package com.example.circuitmessing.startup.auth

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.utils.preferences
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.databinding.FragmentLoginFragmentBinding
import com.example.circuitmessing.startup.classes.User
import kotlinx.coroutines.*

class FragmentLogin : Fragment() {

    companion object {
        fun newInstance() = FragmentLogin()
    }

    private lateinit var viewModel: FragmentLoginViewModel
    private lateinit var loginBinding: FragmentLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        loginBinding = FragmentLoginFragmentBinding.inflate(layoutInflater)
        val view = loginBinding.root

        // Get user data from fragment_login
        val loginUsername = loginBinding.loginUsernameInput.text
        val loginPassword = loginBinding.loginPasswordInput.text
        val loginButton = loginBinding.loginButton

        // Check user login data
        loginButton.setOnClickListener {
            GlobalScope.launch {
                suspend {
                    User.loginUser(loginUsername.toString(), loginPassword.toString())
                    delay(1000)
                    withContext(Dispatchers.Main) {
                        if(!preferences.isConnected) {
                            loginBinding.loginPasswordInput.error = "Wrong username or password"
                        } else {
                            //A way to change activity
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()

                            loginBinding.loginUsernameInput.setText("")
                            loginBinding.loginPasswordInput.setText("")
                        }
                    }
                }.invoke()
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }



}