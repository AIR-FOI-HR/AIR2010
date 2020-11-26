package com.example.circuitmessing.products.nibble

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class Nibble_time_to_get_makin : Fragment() {

    companion object {
        fun newInstance() = Nibble_time_to_get_makin()
    }

    private lateinit var viewModel: NibbleTimeToGetMakinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nibble_time_to_get_makin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NibbleTimeToGetMakinViewModel::class.java)
        // TODO: Use the ViewModel
    }

}