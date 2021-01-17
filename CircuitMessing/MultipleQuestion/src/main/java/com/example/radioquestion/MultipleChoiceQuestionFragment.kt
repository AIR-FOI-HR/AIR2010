package com.example.radioquestion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class MultipleChoiceQuestionFragment(var QuestionText: String,
                                     var Answers: List<String>) : Fragment() {

    lateinit var userAnswers : MutableList<String>
    private lateinit var viewModel: MultipleChoiceQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.multiple_choice_question_fragment, container, false)
        userAnswers = mutableListOf()
        val question = root.findViewById<TextView>(R.id.question_text)
        question.text = QuestionText
        val answer1 = root.findViewById<CheckBox>(R.id.answer1)
        answer1.text = Answers[0]
        val answer2 = root.findViewById<CheckBox>(R.id.answer2)
        answer2.text = Answers[1]
        val answer3 = root.findViewById<CheckBox>(R.id.answer3)
        answer3.text = Answers[2]

        answer1.setOnClickListener {
            if (answer1.isChecked){
                userAnswers.add(answer1.text.toString())
            }
            else{
                userAnswers.remove(answer1.text.toString())
            }
        }

        answer2.setOnClickListener {
            if (answer2.isChecked){
                userAnswers.add(answer1.text.toString())
            }
            else{
                userAnswers.remove(answer1.text.toString())
            }
        }

        answer3.setOnClickListener {
            if (answer3.isChecked){
                userAnswers.add(answer1.text.toString())
            }
            else{
                userAnswers.remove(answer1.text.toString())
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MultipleChoiceQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}