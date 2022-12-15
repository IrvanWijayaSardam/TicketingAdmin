package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailFlightBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentShowListFlightBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFlightFragment : Fragment() {
    private var _binding : FragmentDetailFlightBinding?                                  = null
    private val binding get()                                                            = _binding!!
    var idFlight                                                                         = ""

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
        }
    }

    private fun getArgs() {
        idFlight = arguments?.getInt("idFlight",0).toString()
    }
}