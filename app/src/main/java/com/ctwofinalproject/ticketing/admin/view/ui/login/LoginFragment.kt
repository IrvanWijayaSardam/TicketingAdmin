package com.ctwofinalproject.ticketing.admin.view.ui.login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.auth0.android.jwt.JWT
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.data.Login
import com.ctwofinalproject.ticketing.admin.databinding.FragmentLoginBinding
import com.ctwofinalproject.ticketing.admin.viewmodel.LoginViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?                 = null
    private val binding get()                                   = _binding!!
    val viewModelLogin                                          : LoginViewModel by viewModels()
    val viewModelProto                                          : ProtoViewModel by viewModels()
    lateinit var token                                          : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        token                                                   = ""

        viewModelLogin.getToken().observe(viewLifecycleOwner, {
            if(it != null) {
                Log.d(ContentValues.TAG, "onViewCreated: ${it.accessToken}")
                token = it.accessToken.toString()
                val jwt = JWT(token)
                viewModelProto.editData(jwt.getClaim("firstname").asString().toString(),jwt.getClaim("lastname").asString().toString()
                    ,jwt.getClaim("gender").asString().toString(),jwt.getClaim("email").asString().toString(),jwt.getClaim("phone").asString().toString(),
                    jwt.getClaim("birthdate").asString().toString(),jwt.getClaim("pictures").asString().toString(),token,true)
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
                showSnack("Login Berhasil")
                //goToHome()
            } else {
                showSnack("Username / Password Salah")
            }
        })
    }

    private fun initListener(){
        binding?.run {
            tvDontHaveAnAccountLogin.setOnClickListener {
                //goToSignUp()
            }
            btnApplyLogin.setOnClickListener {
                when {
                    tIetEmailLogin.text.isNullOrEmpty() -> tIetEmailLogin.error = "Email tidak boleh kosong"
                    tIetPasswordLogin.text.isNullOrEmpty() -> tIetPasswordLogin.error = "Password tidak boleh kosong"
                    else -> viewModelLogin.auth(Login(tIetEmailLogin.text.toString(),tIetPasswordLogin.text.toString()))
                }
            }
        }
    }

    fun showSnack(message: String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}