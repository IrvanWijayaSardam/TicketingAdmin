package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
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
import java.util.*

@AndroidEntryPoint
class DetailFlightFragment : Fragment(), TimePickerDialog.OnTimeSetListener {
    private var _binding : FragmentDetailFlightBinding?                                  = null
    private val binding get()                                                            = _binding!!
    lateinit var sharedPref                                                              : SharedPreferences
    lateinit var editPref                                                                : SharedPreferences.Editor
    var idFlight                                                                         = ""
    val detailFlightViewModel                                                            : DetailFlightViewModel by viewModels()

    var hour = 0
    var minute = 0

    var savedHour = 0
    var savedMinute = 0

    var selectTime = ""


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
        sharedPref                                          = requireContext().getSharedPreferences("sharedairport", Context.MODE_PRIVATE)
        editPref                                            = sharedPref.edit()
        getArgs()
        initListener()
        Log.d(TAG, "onViewCreated: ${idFlight}")
    }

    private fun initListener() {
        binding?.run {
            llBookFromFragmentBooking.setOnClickListener {
                gotoSelectAirport("from","home")
            }
            llBookToFragmentBooking.setOnClickListener {
                gotoSelectAirport("to","home")
            }


            ivGotoBackFromFDetailFlight.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }


            iconTimeDepature.setOnClickListener{
                selectTime = "depature"
                getDateTimePicker()
                TimePickerDialog(requireContext(),this@DetailFlightFragment,hour,minute,true).show()
            }

            iconTimeArrival.setOnClickListener{
                selectTime = "arrival"
                getDateTimePicker()
                TimePickerDialog(requireContext(),this@DetailFlightFragment,hour,minute,true).show()
            }

            btnCreateFlight.setOnClickListener{
                detailFlightViewModel.updateFlight(FlightBody(Flight(txtDepature.text.toString(),sharedPref.getInt("airportIdFrom", 0),txtArrivalTime.text.toString(),
                    1,tvDateDepartureDateFragmentSelectOneWay.toString(),1,sharedPref.getInt("airportIdTo",0)),
                    null
                ))
            }

        }
    }
    private fun getDateTimePicker(){
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    fun gotoSelectAirport(fromto : String, fFragment : String){
        var bund = Bundle()
        bund.putString("fromto",fromto)
        bund.putString("fromFragment",fFragment)
        Navigation.findNavController(requireView()).navigate(R.id.action_detailFlightFragment_to_airportFragment,bund)
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

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        if (selectTime.equals("arrival")){
            binding.txtArrivalTime.text = "$savedHour:$savedMinute:00"
        }else{
            binding.txtDepatureTime.text = "$savedHour:$savedMinute:00"
        }
    }
}