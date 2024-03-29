package com.ctwofinalproject.ticketing.admin.view.ui.airportdata

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportDataBinding
import com.ctwofinalproject.ticketing.admin.model.DataItem
import com.ctwofinalproject.ticketing.admin.util.ShowSnack
import com.ctwofinalproject.ticketing.admin.view.adapter.AirportDataAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.AirportViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportDataFragment : Fragment() {
    private var _binding : FragmentAirportDataBinding?                         = null
    private val binding get()                                                  = _binding!!
    val viewModelAirport                                                       : AirportViewModel by viewModels()
    val viewmodelProto                                                         : ProtoViewModel by viewModels()
    lateinit var adapterAirportData                                            : AirportDataAdapter
    private lateinit var builder                                               : AlertDialog.Builder
    var token                                                                  = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAirportDataBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterAirportData                 = AirportDataAdapter()
        builder                             = AlertDialog.Builder(requireActivity())
        viewmodelProto.dataUser.observe(viewLifecycleOwner){
            if(it.isLogin){
                token = it.token
            }
        }


        getAllAirport()
        viewModelAirport.getDataAirport().observe(viewLifecycleOwner) {
            Log.d(ContentValues.TAG, "getAllAirport: $it")
            if (it != null) {
                adapterAirportData.submitList(it.data)
                binding.shimmerBarAirportData.visibility = View.GONE
                binding.rvAirportData.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvAirportData.adapter = adapterAirportData
            }
        }

        viewModelAirport.livedataResposeDeleteAirort.observe(viewLifecycleOwner){
            if(it != null){
                ShowSnack.show(binding.root,"Success Delete Airport")
                getAllAirport()
                viewModelAirport.livedataResposeDeleteAirort.value = null
            }
        }

        adapterAirportData.setOnItemClickListener(object : AirportDataAdapter.onItemClickListener{
            override fun onItemClick(airportData: DataItem) {
                Log.d(TAG, "onItemClick: data yang mau di update ${airportData.code}")
                var bund = Bundle()
                bund.putParcelable("AirportDataDetails",airportData)
                Navigation.findNavController(requireView()).navigate(R.id.action_airportDataFragment_to_airportDataDetailsFragment,bund)
            }

            override fun onItemDelete(airportData: DataItem) {
                builder.setTitle("Delete Item ${airportData.id}")
                    .setMessage("Are you sure want to delete this airprot ?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        //Function delete
                        viewModelAirport.deleteAirport("bearer "+token,airportData.id!!.toInt())
                        dialogInterface.dismiss()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
                    .show()
            }

        })
    }

    private fun getAllAirport() {
        viewModelAirport.fetchAirport()
    }

}