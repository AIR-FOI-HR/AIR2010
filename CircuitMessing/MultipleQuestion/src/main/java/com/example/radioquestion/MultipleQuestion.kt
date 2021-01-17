package com.example.radioquestion

import android.util.Log
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
        Log.d("LOG Answer: ", MultipleChoiceQuestionFragment.userAnswer.toString())
        Log.d("LOG Correct answer: ", CorrectAnswers[0])
        var check: Boolean = false
        for (answer in MultipleChoiceQuestionFragment.userAnswer) {
            if (answer == CorrectAnswers[0]) check = true
        }
        Log.d("LOG Return: ", check.toString())
        return check
    }
}