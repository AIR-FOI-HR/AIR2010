package com.example.circuitmessing.products.ringo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circuitmessing.R

class Ringo_time_to_get_makin_fragment : Fragment() {

    companion object {
        fun newInstance() = Ringo_time_to_get_makin_fragment()
    }

    private lateinit var viewModel: RingoTimeToGetMakinFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.ringo_time_to_get_makin_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingoTimeToGetMakinFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}