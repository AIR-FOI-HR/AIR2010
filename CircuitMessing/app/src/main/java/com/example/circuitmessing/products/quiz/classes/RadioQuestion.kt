package com.example.circuitmessing.products.quiz.classes

import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.circuitmessing.R
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.core.IQuestion
import kotlinx.android.synthetic.main.quiz_question_fragment.*
import kotlin.math.acos

public class RadioQuestion(
    override var QuestionText: String,
    override var Answers: List<String>,
    override var WasAnsweredCorrectly: Boolean = false,
    override var CorrectAnswers: List<String>
) : IQuestion {

    override var QuestionFragment: Fragment = createFragment()

    lateinit var myFrag : Fragment

    fun createFragment(): Fragment{

        myFrag = QuizQuestionFragment(QuestionText, Answers, CorrectAnswers)

        return myFrag
    }

    override fun checkAnswers(answer: String): Boolean {
        return answer == CorrectAnswers[0]
    }
}