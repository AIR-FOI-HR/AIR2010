package com.example.radioquestion

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.core.IQuestion

public class MultipleQuestion(
     var QuestionText: String,
     var Answers: List<String>,
     var CorrectAnswers: List<String>
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    private fun createFragment(): Fragment{
        return MultipleChoiceQuestionFragment(QuestionText, Answers)
    }

    override fun checkAnswers(): Boolean {
        Log.d("LOG Answer: ", MultipleChoiceQuestionFragment.userAnswer.toString())
        Log.d("LOG Correct answer: ", CorrectAnswers[0])
        var trueIndex: Int = 0
        var falseIndex: Int = 0
        for (answer in MultipleChoiceQuestionFragment.userAnswer) {
            if (answer == CorrectAnswers[0]) trueIndex++
            else {
                for (answ in Answers) {
                    if (answer == answ) falseIndex++
                }
            }
        }
        var check: Boolean = (trueIndex > falseIndex)
        Log.d("LOG Return: ", check.toString())
        return check
    }
}