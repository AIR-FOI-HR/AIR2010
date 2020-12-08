package com.example.circuitmessing.products.makerbuino

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class MakerbuinoTimeToGetMakinFragment : Fragment() {

    companion object {
        fun newInstance() = MakerbuinoTimeToGetMakinFragment()
    }

    private lateinit var viewModel: MakerbuinoTimeToGetMakinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.makerbuino_time_to_get_makin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakerbuinoTimeToGetMakinViewModel::class.java)
        // TODO: Use the ViewModel
    }

}