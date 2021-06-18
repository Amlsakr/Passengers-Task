package com.example.passengerstask.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passengerstask.R
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
    private lateinit var homeAdapter : HomeAdapter

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
         homeAdapter = HomeAdapter(requireContext() , this)

        observeData()

        binding.addItem.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_bottomSheetFragment)
        }

        binding.editTextTextSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
               if (s.toString().length >= 1) {
                    homeAdapter.filter.filter(s)
              }else {
                   observeData()
               }
            }

        })


        return view
    }

    override fun onClickItem(item: AirLineItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
        view?.findNavController()?.navigate(action)

    }

    fun observeData(){
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
    }


}