package com.example.circuitmessing.products.nibble

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

class NibbleFinishingUpFragment : Fragment() {

    companion object {
        fun newInstance() = NibbleFinishingUpFragment()
    }

    private lateinit var viewModel: NibbleFinishingUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.nibble_finishing_up_fragment, container, false)

        var image1 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_1)
        var image2 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_2)
        var image3 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_3)
        var image4 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_4)
        var image5 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_5)
        var image6 = root.findViewById<ImageView>(R.id.nibble_finishing_up_image_6)

        Glide.with(this).load("https://api.circuitmess.com/1603883182227-qimhayrw.jpeg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image1)
        Glide.with(this).load("https://api.circuitmess.com/1603883210666-eutwjndq.jpeg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image2)
        Glide.with(this).load("https://api.circuitmess.com/1603883247210-e70_o_1q.jpeg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image3)
        Glide.with(this).load("https://api.circuitmess.com/1603883294682-l_x9wayg.jpeg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image4)
        Glide.with(this).load("https://api.circuitmess.com/1597265344676-dsc02636.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image5)
        Glide.with(this).load("https://api.circuitmess.com/1597265396043-dsc02642.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image6)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NibbleFinishingUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}