package com.ctwofinalproject.ticketing.admin.view.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding : FragmentSplashBinding?               = null
    private val binding get()                                   = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()

    }

    private fun initListener() {
        binding?.run {
            cvGetStarted?.setOnClickListener{
                goToLogin()
            }
        }
    }

    private fun goToLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment)
    }
}