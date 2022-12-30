package com.ctwofinalproject.ticketing.admin.view.ui.listuserbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailUserBookingBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentListUserBookingBinding
import com.ctwofinalproject.ticketing.admin.model.DataItemListBooking
import com.ctwofinalproject.ticketing.admin.util.DecimalSeparator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserBookingFragment : Fragment() {
    private var _binding : FragmentDetailUserBookingBinding?                         = null
    private val binding get()                                                        = _binding!!
    private var dataItemListBooking : DataItemListBooking?                           = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBookingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        getArgs()
    }

    private fun getArgs() {
        dataItemListBooking = requireArguments().getParcelable("dataItemListBooking")
        binding.tvNameUsersFDetailUserBooking.setText(dataItemListBooking!!.users!!.firstname + dataItemListBooking!!.users!!.lastname)
        binding.tvTicketIdFDUserBooking.setText(dataItemListBooking!!.booking!!.ticketIdDeparture.toString())
        binding.tvPriceDepartureFPDBooking.setText("IDR"+DecimalSeparator.formatDecimalSeperators(dataItemListBooking!!.booking!!.ticketDeparture!!.price.toString()))
        binding.tvTotalPassenger.text = dataItemListBooking!!.booking!!.totalPassanger.toString()

        if(dataItemListBooking!!.booking!!.ticketReturn != null){
            binding.tvTicketReturnFDBooking.visibility = View.VISIBLE
            binding.tvDepTimeReturnFBooking.visibility = View.VISIBLE
            binding.tvPriceReturnFDBooking.visibility = View.VISIBLE
            binding.tvDepTimeReturnFBooking.text = dataItemListBooking!!.booking!!.ticketReturn!!.id.toString()
            binding.tvPriceReturnFDBooking.setText("IDR "+DecimalSeparator.formatDecimalSeperators(dataItemListBooking!!.booking!!.ticketReturn!!.price.toString()))
        }
    }

    private fun initListener() {
        binding?.run {
            ivGotoBackFromBookingDetails.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }
}