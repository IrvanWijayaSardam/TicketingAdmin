package com.ctwofinalproject.ticketing.admin.view.ui.flight

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.FragmentAirportBinding
import com.ctwofinalproject.ticketing.admin.databinding.FragmentShowListFlightBinding
import com.ctwofinalproject.ticketing.admin.model.DataItemFlight
import com.ctwofinalproject.ticketing.admin.view.adapter.ShowTicketAdapter
import com.ctwofinalproject.ticketing.admin.viewmodel.ProtoViewModel
import com.ctwofinalproject.ticketing.admin.viewmodel.ShowListFlightViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowListFlightFragment : Fragment() {
    private var _binding : FragmentShowListFlightBinding?                         = null
    private val binding get()                                                     = _binding!!
    lateinit var adapterShowTicket                                                : ShowTicketAdapter
    val viewModelShowListFlight                                                   : ShowListFlightViewModel by viewModels()
    val viewmodelProto                                                            : ProtoViewModel by viewModels()
    private lateinit var builder                                                  : AlertDialog.Builder
    var token                                                                     = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowListFlightBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterShowTicket                                   = ShowTicketAdapter()
        builder                                             = AlertDialog.Builder(requireActivity())

        viewmodelProto.dataUser.observe(viewLifecycleOwner){
            if(it.isLogin){
                token = it.token
                viewModelShowListFlight.getAllFlight("Bearer " + token)
            }
        }

        viewModelShowListFlight.liveDataFlight.observe(viewLifecycleOwner){
            if (it != null) {
                adapterShowTicket.submitList(it.data!!)
                binding.shimmerBar.visibility = View.GONE
                binding.rvShowTicketFragmentShowTicket.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvShowTicketFragmentShowTicket.adapter = adapterShowTicket
            } else {
                binding.rvShowTicketFragmentShowTicket.adapter = null
                binding.shimmerBar.visibility = View.GONE
            }
        }

        adapterShowTicket.setOnItemClickListener(object : ShowTicketAdapter.onItemClickListener{
            override fun onItemClick(dataItemFlight: DataItemFlight) {
                var bund = Bundle()
                bund.putInt("idFlight",dataItemFlight.id!!.toInt())
                bund.putString("depatureTime", dataItemFlight.departureTime.toString())
                bund.putInt("depatureAirport", dataItemFlight.departureAirport!!.toInt())
                bund.putString("depatureDate", dataItemFlight.departureDate.toString())

                bund.putString("arrivalTime", dataItemFlight.arrivalTime.toString())
                bund.putInt("arrivalAirport", dataItemFlight.arrivalAirport!!.toInt())
                bund.putString("arrivalDate", dataItemFlight.arrivalDate.toString())

                bund.putString("depatureAirport", dataItemFlight.departureTerminal!!.name.toString())
                bund.putString("depatureAirportCode", dataItemFlight.departureTerminal.code.toString())

                bund.putString("arrivalAirport", dataItemFlight.arrivalTerminal!!.name.toString())
                bund.putString("arrivalAirportCode", dataItemFlight.arrivalTerminal.code.toString())

                Navigation.findNavController(requireView()).navigate(R.id.action_showListFlightFragment_to_detailFlightFragment,bund)
            }

            override fun onItemDelete(dataItemFlight: DataItemFlight) {
                builder.setTitle("Delete Item ${dataItemFlight.id}")
                    .setMessage("Are you sure want to delete this flight ?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        //Function delete
                        dialogInterface.dismiss()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
                    .show()
            }

        })
    }
}