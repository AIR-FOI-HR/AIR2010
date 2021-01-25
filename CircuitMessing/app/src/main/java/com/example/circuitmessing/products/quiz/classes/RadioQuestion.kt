package com.example.circuitmessing.products.quiz.classes

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.circuitmessing.products.quiz.views.QuizQuestionViewModel
import com.example.core.IQuestion

public class RadioQuestion(
     var QuestionText: String,
     var Answers: List<String>,
     var CorrectAnswers: List<String>

) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    private fun createFragment(): QuizQuestionFragment{
        return QuizQuestionFragment(QuestionText, Answers)
    }

    override fun checkAnswers(): Boolean {
        Log.d("Answer: ", QuizQuestionFragment.userAnswer.toString())
        Log.d("Correct answer: ", CorrectAnswers[0])
        val check: Boolean = (CorrectAnswers[0] == QuizQuestionFragment.userAnswer[0])
        QuizQuestionFragment.userAnswer.removeAt(0)
        return check
    }
}