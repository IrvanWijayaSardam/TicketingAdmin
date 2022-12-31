package com.ctwofinalproject.ticketing.admin.view.ui.user

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.data.UserBody
import com.ctwofinalproject.ticketing.admin.databinding.FragmentDetailUserBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentUserBinding
import com.ctwofinalproject.ticketing.admin.model.Data
import com.ctwofinalproject.ticketing.admin.model.DataUserItem
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserFragment : Fragment() {
    private var _binding : FragmentDetailUserBinding?                           = null
    private val binding get()                                                   = _binding!!
    private var dataUserItem : DataUserItem?                                    = null
    var userId                                                                  = ""
    var token                                                                   = ""
    private val userViewModel                                                   : UserViewModel by viewModels()
    val viewModelProto                                                          : ProtoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBinding.inflate(inflater,container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataUserItem                                                    = DataUserItem()
        initListener()
        getArgs()

        viewModelProto.dataUser.observe(viewLifecycleOwner){
            if(it.isLogin){
                token = it.token
            } else {
                Log.d(TAG, "onViewCreated: Need login")
            }
        }

        userViewModel.liveDataUpdateUser.observe(viewLifecycleOwner){
            if(it!= null){
                if(it.code!!.equals(200)){
                    userViewModel.liveDataUpdateUser.value = null
                    Toast.makeText(context, "User Updated Sucesfully", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(requireView()).navigate(R.id.action_detailUserFragment_to_userFragment)
                } else {
                    Toast.makeText(context, "Failed to update user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initListener(){
        binding.run {
            ivGotoBackFromUserDetails.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
            btnApplyUpdateUser.setOnClickListener {
                var gender = ""
                if(binding.rbGenderMaleUpProfile.isChecked){
                    gender = "L"
                } else {
                    gender = "P"
                }
                userViewModel.updateUser("bearer " + token, userId, UserBody(
                    binding.tICountryUpProfile.text.toString(),
                    binding.tIFirstNameUpUser.text.toString(),
                    binding.tIetBirthdayUpUser.text.toString(),
                    gender,
                    binding.tIProvince.text.toString(),
                    binding.tIphoneNumberUpProfile.text.toString(),
                    binding.tICityUpProfile.text.toString(),
                    binding.tIemailUpUser.text.toString(),
                    null,
                    binding.tILastNameUpUser.text.toString(),
                    binding.tIHomeAddressUpProfile.text.toString(),
                ))
            }
        }
    }
    private fun getArgs(){
        dataUserItem = requireArguments().getParcelable("userDataItem")
        Log.d(TAG, "getArgs: ${dataUserItem}")
        userId = dataUserItem!!.id.toString()
        binding.tIemailUpUser.setText(dataUserItem!!.email!!.toString())
        binding.tIFirstNameUpUser.setText(dataUserItem!!.firstname!!.toString())
        binding.tILastNameUpUser.setText(dataUserItem!!.lastname!!.toString())
        binding.tIphoneNumberUpProfile.setText(dataUserItem!!.phone!!.toString())
        if(dataUserItem!!.address!!.homeAddress != null){
            binding.tIHomeAddressUpProfile.setText(dataUserItem!!.address!!.homeAddress!!.toString())
            binding.tIProvince.setText(dataUserItem!!.address!!.province!!.toString())
            binding.tICityUpProfile.setText(dataUserItem!!.address!!.city!!.toString())
        }
        setProfile(dataUserItem!!.pictures.toString())
    }

    private fun setProfile(urlImage : String){
        Glide.with(this)
            .load(urlImage)
            .error(R.drawable.img_guest)
            .circleCrop()
            .into(binding.ivProfileUpUser)
    }

}