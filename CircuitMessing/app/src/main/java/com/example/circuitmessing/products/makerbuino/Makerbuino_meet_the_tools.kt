package com.example.circuitmessing.products.makerbuino

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class Makerbuino_meet_the_tools : Fragment() {

    companion object {
        fun newInstance() = Makerbuino_meet_the_tools()
    }

    private lateinit var viewModel: MakerbuinoMeetTheToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.makerbuino_meet_the_tools_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakerbuinoMeetTheToolsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}