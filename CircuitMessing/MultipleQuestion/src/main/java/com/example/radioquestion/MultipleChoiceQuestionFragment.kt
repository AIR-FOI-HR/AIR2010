package com.example.radioquestion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class MultipleChoiceQuestionFragment(var QuestionText: String,
                                     var Answers: List<String>,
                                     var CorrectAnswers: List<String>,) : Fragment() {


    private lateinit var viewModel: MultipleChoiceQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.multiple_choice_question_fragment, container, false)

        //val allQuestions = MainActivity.ringoQuiz.DisplayQuestion()

        /*val mybundle : Bundle? = arguments
        val productName = mybundle?.getString("productName")


        val question = root.findViewById<TextView>(R.id.question_text)
        question.text = QuestionText
        val answer1 = root.findViewById<Button>(R.id.answer1)
        answer1.text = Answers[0]
        val answer2 = root.findViewById<Button>(R.id.answer2)
        answer2.text = Answers[1]
        val answer3 = root.findViewById<Button>(R.id.answer3)
        answer3.text = Answers[2]

        var myQuiz : Quiz? = null
        when (productName) {
            "Ringo" -> myQuiz = MainActivity.ringoQuiz
            "Nibble" ->myQuiz =  MainActivity.nibbleQuiz
            "Makerbuino" -> myQuiz = MainActivity.makerbuinoQuiz
        }
        var tmpFragment :Fragment? = myQuiz?.DisplayQuestion()
        if (tmpFragment != null) {
            tmpFragment.arguments = mybundle
        }

        answer1.setOnClickListener {
            if (answer1.text == CorrectAnswers[0]) {
                myQuiz!!.points++

            }
            val transaction : FragmentTransaction = activity?.getSupportFragmentManager()!!.beginTransaction()
            if (myQuiz != null) {
                if (tmpFragment != null) {
                    transaction.replace(R.id.quiz_fragment, tmpFragment)
                }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }


        answer2.setOnClickListener {
            if (answer2.text == CorrectAnswers[0]) {
                myQuiz!!.points++

            }
            val transaction : FragmentTransaction = activity?.getSupportFragmentManager()!!.beginTransaction()
            if (myQuiz != null) {
                if (tmpFragment != null) {
                    transaction.replace(R.id.quiz_fragment, tmpFragment)
                }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }
        answer3.setOnClickListener {
            if (answer3.text == CorrectAnswers[0]) {
                myQuiz!!.points++

            }
            val transaction : FragmentTransaction = activity?.getSupportFragmentManager()!!.beginTransaction()
            if (myQuiz != null) {
                if (tmpFragment != null) {
                    transaction.replace(R.id.quiz_fragment, tmpFragment)
                }
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }*/


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MultipleChoiceQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}