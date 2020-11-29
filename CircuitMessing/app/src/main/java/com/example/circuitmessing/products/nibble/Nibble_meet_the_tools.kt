package com.example.circuitmessing.products.nibble

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class Nibble_meet_the_tools : Fragment() {

    companion object {
        fun newInstance() = Nibble_meet_the_tools()
    }

    private lateinit var viewModel: NibbleMeetTheToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nibble_meet_the_tools_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NibbleMeetTheToolsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}