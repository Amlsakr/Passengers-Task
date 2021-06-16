package com.example.passengerstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.databinding.FragmentHomeBinding
import com.example.passengerstask.view.adapter.HomeAdapter
import com.example.passengerstask.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() , ItemClickListener{

    private lateinit var binding: FragmentHomeBinding
    private val homeFragmentViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        var homeAdapter = HomeAdapter(requireContext() , this)

        homeFragmentViewModel.local.observe(requireActivity(), Observer {
            it.let { res ->

                if ( res != null) {

                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
        //            res.data?.let { it1 -> homeAdapter.setAirLineItemList(it1 as ArrayList<AirLineItem>) }
                    homeAdapter.setAirLineItemList(it as ArrayList<AirLineItem>)
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView.adapter = homeAdapter
                } else {
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
        })



        return view
    }

    override fun onClickItem(item: AirLineItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
        view?.findNavController()?.navigate(action)

    }


}