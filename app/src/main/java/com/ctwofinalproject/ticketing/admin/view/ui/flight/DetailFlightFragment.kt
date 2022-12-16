package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.data.Flight
import com.ctwofinalproject.ticketing.admin.data.FlightBody
import com.ctwofinalproject.ticketing.admin.data.Ticket
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailFlightBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentShowListFlightBinding
import com.ctwofinalproject.ticketing.admin.viewmodel.DetailFlightViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFlightFragment : Fragment() {
    private var _binding : FragmentDetailFlightBinding?                                  = null
    private val binding get()                                                            = _binding!!
    var idFlight                                                                         = ""
    val detailFlightViewModel                                                            : DetailFlightViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailFlightBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        initListener()
        Log.d(TAG, "onViewCreated: ${idFlight}")
    }

    private fun initListener() {
        binding?.run {
            ivGotoBackFromFDetailFlight.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
            btnCreateFlight.setOnClickListener{
                detailFlightViewModel.updateFlight(FlightBody(Flight(txtDepature.toString(),1,txtArrivalTime.toString(),
                    1,tvDateDepartureDateFragmentSelectOneWay.toString(),1,2),
                    Ticket()
                ))
            }

        }
    }

    private fun getArgs() {
        binding.tvFromAirportNameFragmentBooking.text = arguments?.getString("depatureAirport","")
        binding.tvToAirportNameFragmentBooking.text = arguments?.getString("arrivalAirport","")
        binding.tvFromAirportCodeFragmentBooking.text = arguments?.getString("depatureAirportCode", "YIA")
        binding.tvToAirportCodeFragmentBooking.text = arguments?.getString("arrivaAirportCode","Select")

        binding.tvDateDepartureDateFragmentSelectOneWay.text = arguments?.getString("depatureDate", "")
        binding.txtDepature.text = arguments?.getString("depatureTime","")
        binding.txtArrival.text = arguments?.getString("arrivalTime","")

        idFlight = arguments?.getInt("idFlight",0).toString()
    }
}