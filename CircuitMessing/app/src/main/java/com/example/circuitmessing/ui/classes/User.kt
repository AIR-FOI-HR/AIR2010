package com.example.circuitmessing.ui.classes

public class User(username: String, password: String, salt: String, points: Int) {
    val username: String = username
    val password: String = password
    val salt: String = salt
    var points: Int = points
}