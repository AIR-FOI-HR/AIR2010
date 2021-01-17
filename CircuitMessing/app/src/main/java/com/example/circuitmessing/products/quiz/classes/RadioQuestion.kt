package com.example.circuitmessing.products.quiz.classes

import androidx.fragment.app.Fragment
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.core.IQuestion

public class RadioQuestion(
    override var QuestionText: String,
    override var Answers: List<String>,
    override var CorrectAnswers: List<String>
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    fun createFragment(): QuizQuestionFragment{
        return QuizQuestionFragment(QuestionText, Answers)
    }

    override fun checkAnswers(): Boolean {
        return true
    }
}