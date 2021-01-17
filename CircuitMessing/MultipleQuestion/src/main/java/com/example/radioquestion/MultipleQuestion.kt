package com.example.radioquestion

import androidx.fragment.app.Fragment
import com.example.core.IQuestion

public class MultipleQuestion(
    override var QuestionText: String,
    override var Answers: List<String>,
    override var CorrectAnswers: List<String>,
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    fun createFragment(): Fragment{
        return MultipleChoiceQuestionFragment(QuestionText, Answers)
    }

    override fun checkAnswers(): Boolean {
        return true
    }
}