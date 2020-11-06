package com.example.circuitmessing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class makerbuino_introduction : Fragment() {

    companion object {
        fun newInstance() = makerbuino_introduction()
    }

    private lateinit var viewModel: MakerbuinoIntroductionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.makerbuino_introduction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakerbuinoIntroductionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}