package com.example.radioquestion

import androidx.fragment.app.Fragment
import com.example.core.IQuestion

public class MultipleQuestion(
    override var QuestionText: String,
    override var Answers: List<String>,
    override var WasAnsweredCorrectly: Boolean = false,
    override var CorrectAnswers: List<String>,
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    fun createFragment(): Fragment{
        TODO()
    }

    override fun checkAnswers(answer: String): Boolean {
        return answer == CorrectAnswers[0]
    }
}