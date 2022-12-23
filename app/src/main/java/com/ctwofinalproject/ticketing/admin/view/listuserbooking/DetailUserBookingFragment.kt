package com.ctwofinalproject.ticketing.admin.view.listuserbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailUserBookingBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentListUserBookingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserBookingFragment : Fragment() {
    private var _binding : FragmentDetailUserBookingBinding?                         = null
    private val binding get()                                                      = _binding!!
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

    }

}