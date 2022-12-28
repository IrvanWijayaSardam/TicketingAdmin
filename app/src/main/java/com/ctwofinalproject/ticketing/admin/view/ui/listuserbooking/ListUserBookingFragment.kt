package com.ctwofinalproject.ticketing.admin.view.ui.listuserbooking

import android.content.ContentValues.TAG
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
import com.ctwofinalproject.ticketing.admin.databinding.FragmentListUserBookingBinding
import com.ctwofinalproject.ticketing.admin.model.DataItemListBooking
import com.ctwofinalproject.ticketing.admin.view.adapter.UserBookingAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.ListUserBookingViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUserBookingFragment : Fragment() {
    private var _binding : FragmentListUserBookingBinding?                         = null
    private val binding get()                                                      = _binding!!
    private val listUserBookingViewModel                                           : ListUserBookingViewModel by viewModels()
    val viewModelProto                                                             : ProtoViewModel by viewModels()
    lateinit var userBookingAdapter                                                : UserBookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListUserBookingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userBookingAdapter                                                          = UserBookingAdapter()
        initListener()
        viewModelProto.dataUser.observe(viewLifecycleOwner){
            if (it != null){
                listUserBookingViewModel.getListUserBooking("bearer "+it.token)
            } else {

            }
        }

        listUserBookingViewModel.liveDataUserBooking.observe(viewLifecycleOwner){
            if (it != null){
                userBookingAdapter.submitList(it.data!!)
                binding.rvListUserBooking.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                binding.rvListUserBooking.adapter = userBookingAdapter
            }
        }

        userBookingAdapter.setOnItemClickListener(object : UserBookingAdapter.onItemClickListener{
            override fun onItemClick(dataItemListBooking: DataItemListBooking) {
                Log.d(TAG, "onItemClick: ${dataItemListBooking.bookingId}")
                val bund = Bundle()
                bund.putParcelable("dataItemListBooking",dataItemListBooking)
                Navigation.findNavController(requireView()).navigate(R.id.action_listUserBookingFragment_to_detailUserBookingFragment,bund)
            }
        })
    }

    private fun initListener() {
        binding?.run {
            ivGotoBackFromFDetailFlight.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }
}