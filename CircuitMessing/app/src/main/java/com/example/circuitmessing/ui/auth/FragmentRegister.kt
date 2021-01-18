package com.example.circuitmessing.ui.auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.circuitmessing.R
import com.example.circuitmessing.databinding.FragmentRegisterFragmentBinding
import com.example.circuitmessing.ui.classes.User
import kotlinx.coroutines.*

class FragmentRegister : Fragment() {

    companion object {
        fun newInstance() =
            FragmentRegister()
    }

    private lateinit var viewModel: FragmentRegisterViewModel
    private lateinit var registerBinding: FragmentRegisterFragmentBinding
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterFragmentBinding.inflate(layoutInflater)
        val view = registerBinding.root

        // Get user data from register_main
        var username = registerBinding.registerUsernameInput.text
        var password = registerBinding.registerPasswordInput.text
        var repeatPassword = registerBinding.registerPasswordInputRepeat.text
        var registerButton = registerBinding.registerButton

        // Save new user to the database on a click of a register button
        registerButton.setOnClickListener {
            if(password.toString() == repeatPassword.toString()) {
                GlobalScope.launch {
                    suspend {
                        val registerState = User.registerUser(username.toString(), password.toString())
                        delay(1000)
                        withContext(Dispatchers.Main) {
                            if(!registerState){
                                // User successfully created
                                registerBinding.registerUsernameInput.setText("")
                                registerBinding.registerPasswordInput.setText("")
                                registerBinding.registerPasswordInputRepeat.setText("")
                                registerBinding.usernameField.error = null
                                registerBinding.passwordField2.error = null

                            } else {
                                registerBinding.usernameField.error = "User with that username already exists, try again."
                            }
                        }
                    }.invoke()
                }
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
}