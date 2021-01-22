package com.example.circuitmessing.products.nibble

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class NibbleIntroductionFragment : Fragment() {

    companion object {
        fun newInstance() = NibbleIntroductionFragment()
    }

    private lateinit var viewModel: NibbleIntroductionFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nibble_introduction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NibbleIntroductionFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}