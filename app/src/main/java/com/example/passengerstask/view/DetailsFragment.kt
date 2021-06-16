package com.example.passengerstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passengerstask.R
import com.example.passengerstask.databinding.FragmentDetailsBinding
import com.example.passengerstask.databinding.FragmentSplashScreenBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
      val model = DetailsFragmentArgs.fromBundle(requireArguments()).airLineItem
        binding.name.text = model.name
        Picasso.get().load(model.logo).into(binding.logo)

        return view
    }



}