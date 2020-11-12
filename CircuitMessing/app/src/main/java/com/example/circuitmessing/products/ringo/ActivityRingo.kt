package com.example.circuitmessing.products.ringo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.circuitmessing.R

class ActivityRingo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ringo)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}