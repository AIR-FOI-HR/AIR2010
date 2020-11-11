package com.example.circuitmessing.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.circuitmessing.*
import com.example.circuitmessing.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _homeView: FragmentHomeBinding? = null

    private val homeView get() = _homeView!!



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        _homeView = FragmentHomeBinding.inflate(inflater, container, false)
        val view = homeView.root



        homeView.homeRingoButton.setOnClickListener {
            val intent = Intent(this.activity, ActivityRingo::class.java)
            startActivity(intent)
            this.activity?.finish()
        }

        homeView.homeMakerButton.setOnClickListener {
            val intent = Intent(this.activity, MakerbuinoActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        }

        homeView.homeNibbleButton.setOnClickListener {
            val intent = Intent(this.activity, ActivityNibble::class.java)
            startActivity(intent)
            this.activity?.finish()
        }

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeView = null
    }
}