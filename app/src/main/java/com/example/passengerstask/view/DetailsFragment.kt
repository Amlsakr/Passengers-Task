package com.example.passengerstask.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.passengerstask.R
import com.example.passengerstask.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
      val model = DetailsFragmentArgs.fromBundle(requireArguments()).airLineItem
        binding.name.text = model.name
        binding.slogan.text = model.slogan
        binding.country.text = model.country
        binding.headQuarter.text = model.headQuaters
        Picasso.get().load(model.logo).into(binding.logo)
        binding.visitButton.setOnClickListener {
            if(model.website != null &&  !model.website.isEmpty()){
                startActivity( Intent(Intent.ACTION_VIEW, Uri.parse("http://" +model.website)))
            }

        }
        binding.backIV.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment)
        }

        return view
    }



}