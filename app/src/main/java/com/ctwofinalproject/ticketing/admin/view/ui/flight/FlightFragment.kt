package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentFlightBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightFragment : Fragment() {

    private var _binding : FragmentFlightBinding?                          = null
    private val binding get()                                              = _binding!!
    lateinit var sharedPref                                                : SharedPreferences
    lateinit var editPref                                                  : SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref                                          = requireContext().getSharedPreferences("sharedairport", Context.MODE_PRIVATE)
        editPref                                            = sharedPref.edit()
        initListener()

    }

    private fun initListener() {
        binding?.run {
            cvOneWaySearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.primary_blue_1))
            cvRoundTripSearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.secondary_font_color))

            llBookFromFragmentBooking.setOnClickListener {
                gotoSelectAirport("from","home")
            }
            llBookToFragmentBooking.setOnClickListener {
                gotoSelectAirport("to","home")
            }
            ivGotoBackFromFragmentFlight.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }

            cvOneWaySearchFlightFragmentBooking.setOnClickListener {
//            switch to framgent SelectOneWayFragment
                cvOneWaySearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.primary_blue_1))
                cvRoundTripSearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.secondary_font_color))
                val selectOneWayFragment = SelectOneWayFragment()
                val manager = activity?.supportFragmentManager
                val transaction = manager?.beginTransaction()
                transaction?.replace(R.id.fragmentContainerViewDepartureAndREturnDateFragmentBooking, selectOneWayFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
            cvRoundTripSearchFlightFragmentBooking.setOnClickListener {
                cvRoundTripSearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.primary_blue_1))
                cvOneWaySearchFlightFragmentBooking.setCardBackgroundColor(resources.getColor(R.color.secondary_font_color))
                //            switch to framgent SelectRoundTripFragment
                val selectRoundTripFragment = SelectRoundTripFragment()
                val manager = activity?.supportFragmentManager
                val transaction = manager?.beginTransaction()
                transaction?.replace(R.id.fragmentContainerViewDepartureAndREturnDateFragmentBooking, selectRoundTripFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }


            tvFromAirportCodeFragmentBooking.text = sharedPref.getString("airportCodeFrom","YIA")
            tvFromAirportNameFragmentBooking.text = sharedPref.getString("airportNameFrom","AirportName")

            tvToAirportCodeFragmentBooking.text   = sharedPref.getString("airportCodeTo","Select")
            tvToAirportNameFragmentBooking.text   = sharedPref.getString("airportNameTo","Airport Name")
        }
    }

    fun gotoSelectAirport(fromto : String, fFragment : String){
        var bund = Bundle()
        bund.putString("fromto",fromto)
        bund.putString("fromFragment",fFragment)
        Navigation.findNavController(requireView()).navigate(R.id.action_flightFragment_to_airportFragment,bund)
    }

}