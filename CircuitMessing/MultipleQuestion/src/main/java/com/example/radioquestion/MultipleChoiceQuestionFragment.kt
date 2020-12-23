package com.example.radioquestion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MultipleChoiceQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = MultipleChoiceQuestionFragment()
    }

    private lateinit var viewModel: MultipleChoiceQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.multiple_choice_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MultipleChoiceQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}