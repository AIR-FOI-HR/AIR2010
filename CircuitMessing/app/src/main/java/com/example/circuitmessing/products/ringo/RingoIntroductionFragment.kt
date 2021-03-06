package com.example.circuitmessing.products.ringo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class RingoIntroductionFragment : Fragment() {

    companion object {
        fun newInstance() = RingoIntroductionFragment()
    }

    private lateinit var viewModel: RingoIntroductionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ringo_introduction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingoIntroductionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}