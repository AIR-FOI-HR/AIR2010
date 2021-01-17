package com.example.circuitmessing.products.quiz.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.quiz.classes.Quiz

class QuizQuestionFragment(var QuestionText: String,
                           var Answers: List<String>) : Fragment() {

    private lateinit var viewModel: QuizQuestionViewModel

    companion object {
        var userAnswer : MutableList<String> = arrayListOf()
        fun setAnswer(answer: String) {
            userAnswer.add(answer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.quiz_question_fragment, container, false)

        val question = root.findViewById<TextView>(R.id.question_text)
        question.text = QuestionText
        val answer1 = root.findViewById<Button>(R.id.answer1)
        answer1.text = Answers[0]
        val answer2 = root.findViewById<Button>(R.id.answer2)
        answer2.text = Answers[1]
        val answer3 = root.findViewById<Button>(R.id.answer3)
        answer3.text = Answers[2]

        answer1.setOnClickListener {
            setAnswer(answer1.text.toString())
            answer1.alpha = 0.5F
            answer2.alpha = 1F
            answer3.alpha = 1F
        }

        answer2.setOnClickListener {
            setAnswer(answer2.text.toString())
            answer1.alpha = 1F
            answer2.alpha = 0.5F
            answer3.alpha = 1F
        }

        answer3.setOnClickListener {
            setAnswer(answer3.text.toString())
            answer1.alpha = 1F
            answer2.alpha = 1F
            answer3.alpha = 0.5F
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}