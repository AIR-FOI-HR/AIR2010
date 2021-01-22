package com.example.circuitmessing.products.makerbuino

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class MakerbuinoIntroductionFragment : Fragment() {

    companion object {
        fun newInstance() = MakerbuinoIntroductionFragment()
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