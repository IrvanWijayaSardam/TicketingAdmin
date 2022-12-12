package com.ctwofinalproject.ticketing.admin.view.ui.airport

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportBinding
import com.ctwofinalproject.ticketing.admin.model.DataItem
import com.ctwofinalproject.ticketing.admin.view.adapter.AirportAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.AirportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportFragment : Fragment() {
    private var _binding : FragmentAirportBinding?                         = null
    private val binding get()                                              = _binding!!
    lateinit var sharedPref                                                : SharedPreferences
    lateinit var editPref                                                  : SharedPreferences.Editor
    val viewModelAirport                                                   : AirportViewModel by viewModels()
    lateinit var adapterAirport                                            : AirportAdapter
    lateinit var fromto                                                    : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirportBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref                                          = requireContext().getSharedPreferences("sharedairport", Context.MODE_PRIVATE)
        editPref                                            = sharedPref.edit()
        adapterAirport                                      = AirportAdapter()
        fromto                                              = ""

        getAllAirport()
        initListener()
        getArgs()

        viewModelAirport.getDataAirport().observe(viewLifecycleOwner) {
            Log.d(ContentValues.TAG, "getAllAirport: $it")
            if (it != null) {
                adapterAirport.submitList(it.data)
                binding.shimmerBar.visibility = View.GONE
                binding.rvAirport.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvAirport.adapter = adapterAirport
            }
        }

        viewModelAirport.getDataAirportSearch().observe(viewLifecycleOwner) {
            Log.d(ContentValues.TAG, "getAllAirport: $it")
            if (it != null) {
                adapterAirport.submitList(it.data)
                binding.shimmerBar.visibility = View.GONE
                binding.rvAirport.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvAirport.adapter = adapterAirport
            }
        }

        adapterAirport.setOnItemClickListener(object : AirportAdapter.onItemClickListener{
            override fun onItemClick(airportData: DataItem) {
                when(fromto) {
                    "from" -> {
                        Log.d(ContentValues.TAG, "onItemClick: ${airportData.city}")
                        editPref.putInt("airportIdFrom", airportData.id!!)
                        editPref.putString("airportNameFrom",airportData.name)
                        editPref.putString("airportCodeFrom",airportData.code)
                    }
                    "to" -> {
                        Log.d(ContentValues.TAG, "onItemClick: ${airportData.city}")
                        editPref.putInt("airportIdTo", airportData.id!!)
                        editPref.putString("airportNameTo",airportData.name)
                        editPref.putString("airportCodeTo",airportData.code)
                    }
                }
                editPref.apply()
                Navigation.findNavController(requireView()).navigate(R.id.action_airportFragment_to_flightFragment)
            }
        })

    }

    private fun initListener() {
        binding?.run {


            ivSearch.setOnClickListener {
                viewModelAirport.searchAirport(tIetSearchAirportFragmentAirport.text.toString())
            }
            ivBackFromChooseAirportFragmentAirport.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }

    private fun getAllAirport() {
        viewModelAirport.fetchAirport()
    }

    private fun getArgs() {
        fromto = arguments?.getString("fromto").toString()
    }

}