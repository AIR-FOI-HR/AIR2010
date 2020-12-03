package com.example.circuitmessing.products.makerbuino

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.circuitmessing.R

class MakerbuinoFinishingUpFragment : Fragment() {

    companion object {
        fun newInstance() = MakerbuinoFinishingUpFragment()
    }

    private lateinit var viewModel: MakerbuinoFinishingUpFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.makerbuino_finishing_up_fragment, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakerbuinoFinishingUpFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}