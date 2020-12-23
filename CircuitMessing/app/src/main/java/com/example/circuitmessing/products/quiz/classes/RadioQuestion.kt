package com.example.circuitmessing.products.quiz.classes

import androidx.fragment.app.Fragment
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.core.IQuestion

public class RadioQuestion(
    override var QuestionText: String,
    override var Answers: List<String>,
    override var WasAnsweredCorrectly: Boolean = false,
    override var CorrectAnswers: List<String>,
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    fun createFragment(): Fragment{
        return QuizQuestionFragment()
    }

    override fun checkAnswers(answer: String): Boolean {
        return answer == CorrectAnswers[0]
    }
}