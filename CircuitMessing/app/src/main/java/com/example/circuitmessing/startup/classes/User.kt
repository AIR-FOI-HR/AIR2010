package com.example.circuitmessing.startup.classes

import com.example.circuitmessing.utils.preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom


public class User(username: String, password: String, salt: String, points: Int) {
    val username: String = username
    val password: String = password
    val salt: String = salt
    var points: Int = points



    companion object {
        // Instance of the database reference
        private val database = Firebase.database.reference

        private fun createNewUser(username: String, password: String): User {
            val salt = User.generateSalt().toString()
            val hashedPassword = User.getSHA512(password + salt)
            return User(username = username, password = hashedPassword, salt = salt, points = 0)
        }

        fun loginUser(username: String, password: String) {
            val usersRef = database.child("users").orderByChild("username").equalTo(username)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val dbUsername = ds.child("username").getValue(String::class.java)
                        val dbPassword = ds.child("password").getValue(String::class.java)
                        val dbSalt = ds.child("salt").getValue(String::class.java)

                        val hashedPassword = getSHA512( password + dbSalt.toString())

                        if(dbUsername == username && dbPassword == hashedPassword){
                            // Once the user connected, we can keep the session to avoid reconnection at each launch
                            // Also used in LoginActivity
                            preferences.isConnected = true
                            preferences.username = username
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            usersRef.addListenerForSingleValueEvent(valueEventListener)
        }

        fun registerUser(username: String, password: String): Boolean {
            val user = createNewUser(username.toString(), password.toString())
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
                        User.database.child("users").child(username).setValue(user)
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            usersRef.addListenerForSingleValueEvent(valueEventListener)
            return foundUserInDatabase
        }

        private fun generateSalt(): ByteArray {
            val random = SecureRandom()
            val salt = ByteArray(16)
            random.nextBytes(salt)

            return salt
        }

        fun getSHA512(input:String):String{
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
}