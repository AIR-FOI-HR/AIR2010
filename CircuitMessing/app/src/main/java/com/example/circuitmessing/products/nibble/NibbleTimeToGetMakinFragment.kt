package com.example.circuitmessing.products.nibble

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class NibbleTimeToGetMakinFragment : Fragment() {

    companion object {
        fun newInstance() = NibbleTimeToGetMakinFragment()
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