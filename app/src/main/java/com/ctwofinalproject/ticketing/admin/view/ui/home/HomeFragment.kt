package com.ctwofinalproject.ticketing.admin.view.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?                           = null
    private val binding get()                                             = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {

        binding?.run {
            flight.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_chooseActionFragment)
            }
            user.setOnClickListener{
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_userFragment)
            }
            airport.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_airportDataFragment)
            }
        }
    }
}