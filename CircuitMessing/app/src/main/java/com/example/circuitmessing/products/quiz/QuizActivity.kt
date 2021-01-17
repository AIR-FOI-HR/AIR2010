package com.example.circuitmessing.products.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.quiz.classes.Quiz
import com.example.circuitmessing.products.quiz.views.EndQuizFragment
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.circuitmessing.products.quiz.views.StartQuizFragment
import kotlin.reflect.typeOf

class QuizActivity : AppCompatActivity() {

    lateinit var productName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val product : Bundle? = intent.extras

        if(product != null) {
            viewManagement(product)

            val transaction : FragmentTransaction = getSupportFragmentManager().beginTransaction()
            val myFrag = StartQuizFragment()

            transaction.replace(R.id.quiz_fragment, myFrag)
            transaction.addToBackStack(null)
            transaction.commit()

            var confirmButton = findViewById<Button>(R.id.confirm_quiz)
            confirmButton.setOnClickListener {
                switchQuestion(product)
            }
        }
    }

    private fun switchQuestion(product: Bundle) {
        productName = product.getString("product").toString()

        var currentQuiz: Quiz? = null
        when(productName){
            "ringo" -> currentQuiz = MainActivity.ringoQuiz
            "nibble" -> currentQuiz = MainActivity.nibbleQuiz
            "makerbuino" -> currentQuiz = MainActivity.makerbuinoQuiz
        }

        var questionFrag: Fragment = currentQuiz!!.DisplayQuestion()

        if (currentQuiz.index == -1){
            var confirmButton = findViewById<Button>(R.id.confirm_quiz)
            confirmButton.isVisible = false
        }

        val transaction : FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.quiz_fragment, questionFrag)
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
        productName = "Makerbuino"

    }

    private fun nibbleViewManagement(toolbar: Toolbar)
    {
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
        toolbar.title = "Nibble"
        productName = "Nibble"
    }

    private fun ringoViewManagement(toolbar: Toolbar)
    {
        toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark))
        toolbar.title = "Ringo"
        productName = "Ringo"
    }
}