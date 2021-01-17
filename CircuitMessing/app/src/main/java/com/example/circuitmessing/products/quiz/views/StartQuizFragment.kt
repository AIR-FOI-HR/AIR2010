package com.example.circuitmessing.products.quiz.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R

class StartQuizFragment : Fragment() {

    companion object {
        fun newInstance() =
            StartQuizFragment()
    }

    private lateinit var viewModel: StartQuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.start_quiz_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartQuizViewModel::class.java)
        // TODO: Use the ViewModel
    }

}