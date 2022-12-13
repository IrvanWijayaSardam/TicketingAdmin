package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentChooseActionBinding


class ChooseActionFragment : Fragment() {

    lateinit var binding : FragmentChooseActionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChooseActionBinding.inflate(inflater, container, false)
        return binding.root
    }

}