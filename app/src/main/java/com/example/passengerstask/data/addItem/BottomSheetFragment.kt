package com.example.passengerstask.data.addItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.passengerstask.R
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.databinding.FragmentBottomSheetBinding
import com.example.passengerstask.databinding.FragmentSplashScreenBinding
import com.example.passengerstask.viewModel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private val homeFragmentViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        val view = binding.root

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.confirmButton.setOnClickListener {
            val name = binding.editTextTextAirLineName.text.toString()
            val country = binding.editTextTextCountry.text.toString()
            val slogan = binding.editTextTextSlogan.text.toString()
            val headQuarter = binding.editTextTextHeadQuarter.text.toString()
            val id = generateID()
            val  item = AirLineItem(null,country,null,name,headQuarter,null,id,slogan)
            homeFragmentViewModel.add(item)
        }


        return view
    }

    private fun generateID(): Double {
        var id = 0.0
        GlobalScope.launch (Dispatchers.Main) {
            val list = homeFragmentViewModel.getJobsFromDataBase()
            val listOfIDS = ArrayList<Double>()
            for (item in list){
                listOfIDS.add(item.id)
            }
            Collections.sort(listOfIDS);
            id = listOfIDS.lastIndex + 1.0
        }
        return id

    }


}