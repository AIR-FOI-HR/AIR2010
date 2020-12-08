package com.example.circuitmessing.products.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        val product : Bundle? = intent.extras
        if(product != null)
            viewManagement(product)

        val transaction : FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.quiz_fragment, QuizQuestionFragment())
        transaction.addToBackStack(null)
        transaction.commit()


    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun viewManagement(product: Bundle)
    {
        val toolbar : Toolbar = findViewById(R.id.quiz_toolbar)

        when (product.getString("product")) {
            "ringo" -> ringoViewManagement(toolbar)
            "nibble" -> nibbleViewManagement(toolbar)
            "makerbuino" -> makerbuinoViewManagement(toolbar)
            else -> { // Note the block
                //TODO display error fragment
            }
        }
    }
    private fun makerbuinoViewManagement(toolbar: Toolbar)
    {
        toolbar.setBackgroundColor(getColor(R.color.colorAccent))
        toolbar.title = "Makerbuino"
    }

    private fun nibbleViewManagement(toolbar: Toolbar)
    {
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
        toolbar.title = "Nibble"
    }

    private fun ringoViewManagement(toolbar: Toolbar)
    {
        toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark))
        toolbar.title = "Ringo"
    }
}