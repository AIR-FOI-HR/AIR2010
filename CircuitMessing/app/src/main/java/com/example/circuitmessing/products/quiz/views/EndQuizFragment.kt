package com.example.circuitmessing.products.quiz.views

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.circuitmessing.MainActivity
import com.example.circuitmessing.R
import com.example.circuitmessing.products.quiz.QuizActivity
import org.w3c.dom.Text

class EndQuizFragment(var Points: Int, var Title: String) : Fragment() {
    private lateinit var viewModel: EndQuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.end_quiz_fragment, container, false)

        val end_button = root.findViewById<Button>(R.id.end_quiz)
        val points = root.findViewById<TextView>(R.id.achieved_score)
        val titles = root.findViewById<TextView>(R.id.achieved_titles)

        points.text = Points.toString()
        titles.text = Title
        end_button.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EndQuizViewModel::class.java)
        // TODO: Use the ViewModel
    }

}