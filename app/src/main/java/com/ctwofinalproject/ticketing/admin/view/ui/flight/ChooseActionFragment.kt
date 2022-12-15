package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentChooseActionBinding


class ChooseActionFragment : Fragment() {

    lateinit var binding : FragmentChooseActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {

        binding?.run {
            btnInputDataFlight.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_chooseActionFragment_to_flightFragment)
            }
            btnSeeListFlight.setOnClickListener{
                Navigation.findNavController(requireView()).navigate(R.id.showListFlightFragment)
            }
        }
    }

}