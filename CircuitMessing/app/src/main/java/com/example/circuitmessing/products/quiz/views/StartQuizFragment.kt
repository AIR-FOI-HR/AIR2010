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

        val startButton : Button = root.findViewById(R.id.start_quiz_button)

        val mybundle : Bundle? = arguments

        startButton.setOnClickListener {

            val transaction : FragmentTransaction = activity?.getSupportFragmentManager()!!.beginTransaction()

            val productName = mybundle?.getString("productName")
            var myFrag : Fragment? = null
            when (productName) {
                "Ringo"-> {myFrag = MainActivity.ringoQuiz.DisplayQuestion() }
                "Nibble"-> {myFrag = MainActivity.nibbleQuiz.DisplayQuestion() }
                "Makerbuino"-> {myFrag = MainActivity.makerbuinoQuiz.DisplayQuestion() }
            }
            if (myFrag != null) {
                myFrag.arguments = mybundle
            }

            //TODO display question return questions and implementation in quizquestionfragment
            transaction.replace(R.id.quiz_fragment, myFrag!!)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartQuizViewModel::class.java)
        // TODO: Use the ViewModel
    }

}