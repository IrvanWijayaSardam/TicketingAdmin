package com.ctwofinalproject.ticketing.admin.view.ui.user

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.dataStore
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentHomeBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentUserBinding
import com.ctwofinalproject.ticketing.admin.model.DataUserItem
import com.ctwofinalproject.ticketing.admin.view.adapter.AirportAdapter
import com.ctwofinalproject.ticketing.admin.view.adapter.UserAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding?                           = null
    private val binding get()                                             = _binding!!
    lateinit var adapterUserAdapter                                       : UserAdapter
    private val userViewModel                                             : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterUserAdapter                                              = UserAdapter()

        userViewModel.getAllUser()
        userViewModel.liveDataUser.observe(viewLifecycleOwner){
            if(it != null){
                adapterUserAdapter.submitList(it.data!!)
                binding.rvUserFUser.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvUserFUser.adapter = adapterUserAdapter
            }
        }

        adapterUserAdapter.setOnItemClickListener(object : UserAdapter.onItemClickListener {
            override fun onItemClick(userDataItem: DataUserItem) {
                Log.d(TAG, "onItemClick: data yang mau di update ${userDataItem.email} ")
                var bund = Bundle()
                bund.putString("namaUser", userDataItem.firstname + " " + userDataItem.lastname)
                bund.putString("emailUser", userDataItem.email)
                bund.putString("passwordUser", userDataItem.password)
                bund.putString("genderUser", userDataItem.gender)
                bund.putString("addressUser", userDataItem.address!!.homeAddress + " " + userDataItem.address.city + " "+ userDataItem.address.province)

                Navigation.findNavController(requireView()).navigate(R.id.action_userFragment_to_detailUserFragment)
            }

            override fun onItemDelete(userDataItem: DataUserItem) {
                Log.d(TAG, "onItemClick: data yang mau di delete ${userDataItem.email} ")
            }

        })

    }

}