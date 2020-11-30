package com.example.circuitmessing.products.ringo

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


class RingoFinishingUpFragment : Fragment() {

    companion object {
        fun newInstance() = RingoFinishingUpFragment()
    }

    private lateinit var viewModel: RingoFinishingUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.ringo_finishing_up_fragment, container, false)

        var image1 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_1)
        var image2 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_2)
        var image3 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_3)
        var image4 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_4)
        var image5 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_5)
        var image6 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_6)
        var image7 = root.findViewById<ImageView>(R.id.ringo_finishing_up_image_7)

        Glide.with(this).load("https://api.circuitmess.com/1586972541871-DSC05948_1024x683.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image1)
        Glide.with(this).load("https://api.circuitmess.com/1586972552029-SIMt.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image2)
        Glide.with(this).load("https://api.circuitmess.com/1586972578036-DSC05950_1024x683.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image3)
        Glide.with(this).load("https://api.circuitmess.com/1586972607588-SIM-Final-v2-1.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image4)
        Glide.with(this).load("https://api.circuitmess.com/1586972618188-DSC05987_1024x683crop.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image5)
        Glide.with(this).load("https://api.circuitmess.com/1586972632428-DSC05993_1024x683crop.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image6)
        Glide.with(this).load("https://api.circuitmess.com/1586972642472-DSC05999_1024x683crop.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image7)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingoFinishingUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}