package com.ctwofinalproject.ticketing.admin.view.ui.user

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
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
import com.ctwofinalproject.ticketing.admin.util.ShowSnack
import com.ctwofinalproject.ticketing.admin.view.adapter.AirportAdapter
import com.ctwofinalproject.ticketing.admin.view.adapter.UserAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding?                           = null
    private val binding get()                                             = _binding!!
    lateinit var adapterUserAdapter                                       : UserAdapter
    private val userViewModel                                             : UserViewModel by viewModels()
    val viewModelProto                                                    : ProtoViewModel by viewModels()
    private lateinit var builder                                          : AlertDialog.Builder
    var token                                                             = ""
    var userId                                                            = ""


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
        viewModelProto.dataUser.observe(viewLifecycleOwner){
            if(it.isLogin){
                token = it.token
            } else {
                Log.d(TAG, "onViewCreated: require login")
            }
        }

        userViewModel.liveDataDeleteUser.observe(viewLifecycleOwner){
            if(it != null){
                if(it.code!!.equals(200)){
                    ShowSnack.show(binding.root,"User Deleted")
                    userViewModel.getAllUser()
                    userViewModel.liveDataDeleteUser.value = null
                }
            }
        }

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
                bund.putParcelable("userDataItem",userDataItem)

                Navigation.findNavController(requireView()).navigate(R.id.action_userFragment_to_detailUserFragment,bund)
            }

            override fun onItemDelete(userDataItem: DataUserItem) {
//                builder.setTitle("Delete Item ${userDataItem.id}")
//                    .setMessage("Are you sure want to delete this flight ?")
//                    .setCancelable(true)
//                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
//                        //Function delete
//
//                        dialogInterface.dismiss()
//                    })
//                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
//                        dialogInterface.dismiss()
//                    })
//                    .show()
                userViewModel.deleteUser("bearer "+token, userDataItem.id!!)


            }

        })

    }

}