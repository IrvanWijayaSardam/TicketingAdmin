package com.ctwofinalproject.ticketing.admin.view.ui.airportdata

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.data.AirportBody
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportDataBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportDataDetailsBinding
import com.ctwofinalproject.ticketing.admin.model.Data
import com.ctwofinalproject.ticketing.admin.model.DataItem
import com.ctwofinalproject.ticketing.admin.viewmodel.AirportViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportDataDetailsFragment : Fragment() {

    private var _binding : FragmentAirportDataDetailsBinding?                         = null
    private val binding get()                                                         = _binding!!
    private var dataAirport : DataItem?                                               = null
    val viewModelAirport                                                              : AirportViewModel by viewModels()
    val viewModelProto                                                                : ProtoViewModel by viewModels()
    var token                                                                         = ""
    var idAirport                                                                     = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAirportDataDetailsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAirport                                 = DataItem()

        initListener()
        getArgs()

        viewModelProto.dataUser.observe(viewLifecycleOwner){
            if(it.token != null){
                token = it.token
            } else {
                Log.d(TAG, "onViewCreated: Need Login")
            }
        }

        viewModelAirport.liveDataResponseUpdate.observe(viewLifecycleOwner){
            if(it != null){
                if(it.code!!.equals(200)){
                    Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(requireView()).navigate(R.id.action_airportDataDetailsFragment_to_airportDataFragment)
                    viewModelAirport.liveDataResponseUpdate.value = null
                } else {
                    Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getArgs() {
        dataAirport = requireArguments().getParcelable("AirportDataDetails")
        idAirport = dataAirport!!.id.toString()
        binding.tINameAirport.setText(dataAirport!!.name!!.toString())
        binding.tICodeAirport.setText(dataAirport!!.code!!.toString())
        binding.tICityAirport.setText(dataAirport!!.city!!.toString())
        binding.tICountryAirport.setText(dataAirport!!.country.toString())
        binding.tITerminalAirport.setText(dataAirport!!.terminal.toString())
    }

    private fun initListener() {
        binding?.run {
            btnApplyUpdateAirportData.setOnClickListener {
                viewModelAirport.updateAirport("bearer "+token,idAirport, AirportBody(binding.tICountryAirport.text.toString(),binding.tICodeAirport.text.toString(),binding.tICityAirport.text.toString(),binding.tINameAirport.text.toString()))
            }
        }
    }


}