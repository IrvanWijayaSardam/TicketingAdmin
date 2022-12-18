package com.ctwofinalproject.ticketing.admin.view.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailUserBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentUserBinding

class DetailUserFragment : Fragment() {
    private var _binding : FragmentDetailUserBinding?                           = null
    private val binding get()                                             = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBinding.inflate(inflater,container, false)
        return inflater.inflate(R.layout.fragment_detail_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        getArgs()
    }

    private fun initListener(){
        binding.run {
            ivGotoBackFromFDetailFlight.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }

        }
    }
    private fun getArgs(){
        binding.tvEmailUser.text = arguments?.getString("emailUser", "")
        binding.tvNameUser.text = arguments?.getString("namaUser","")
    }

}