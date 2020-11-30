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

        var image1 = root.findViewById<ImageView>(R.id.maker_finishing_up_image_1)
        var image2 = root.findViewById<ImageView>(R.id.maker_finishing_up_image_2)
        var image3 = root.findViewById<ImageView>(R.id.maker_finishing_up_image_3)
        var image4 = root.findViewById<ImageView>(R.id.maker_finishing_up_image_4)

        Glide.with(this).load("https://api.circuitmess.com/1587030434349-DSC05576_800x533.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image1)
        Glide.with(this).load("https://api.circuitmess.com/1587030456907-DSC05586_800x533.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image2)
        Glide.with(this).load("https://api.circuitmess.com/1587030473147-DSC05606_800x533.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image3)
        Glide.with(this).load("https://api.circuitmess.com/1587030492308-36259739255_c345cfabfd_o_1280x851.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image4)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakerbuinoFinishingUpFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}