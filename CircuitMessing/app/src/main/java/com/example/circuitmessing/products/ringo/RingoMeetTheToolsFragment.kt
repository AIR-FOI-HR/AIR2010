package com.example.circuitmessing.products.ringo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class RingoMeetTheToolsFragment : Fragment() {

    companion object {
        fun newInstance() = RingoMeetTheToolsFragment()
    }

    private lateinit var viewModel: RingoMeetTheToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ringo_meet_the_tools_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingoMeetTheToolsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}