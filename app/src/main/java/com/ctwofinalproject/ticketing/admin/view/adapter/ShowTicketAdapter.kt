package com.ctwofinalproject.ticketing.admin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctwofinalproject.ticketing.admin.databinding.ItemShowTicketBinding
import com.ctwofinalproject.ticketing.admin.model.DataItemFlight
import com.ctwofinalproject.ticketing.admin.util.DecimalSeparator

class ShowTicketAdapter(): RecyclerView.Adapter<ShowTicketAdapter.ViewHolder>() {
    private lateinit var listener: onItemClickListener

    private val diffCallback = object : DiffUtil.ItemCallback<DataItemFlight>(){
        override fun areItemsTheSame(oldItem: DataItemFlight, newItem: DataItemFlight): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItemFlight, newItem: DataItemFlight): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    fun submitList(dataItemFlight: List<DataItemFlight?>) = differ.submitList(dataItemFlight)

    private val differ = AsyncListDiffer(this,diffCallback)

    inner class ViewHolder(val binding: ItemShowTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnUpdate.setOnClickListener {
                listener.onItemClick(differ.currentList[adapterPosition])
            }
            binding.btnDelete.setOnClickListener {
                listener.onItemDelete(differ.currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemShowTicketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDepTimeItemShowTicket.text = differ.currentList[position].departureTime
        holder.binding.tvArrTimeItemShowTicket.text = differ.currentList[position].arrivalTime
        holder.binding.tvFlightNumberItemShowTicket.text = differ.currentList[position].id.toString()
        holder.binding.tvFlightFrom.setText("Flight From ${differ.currentList[position].departureTerminal!!.code} to ${differ.currentList[position].arrivalTerminal!!.code}")
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface onItemClickListener {
        fun onItemClick(dataItemFlight: DataItemFlight)
        fun onItemDelete(dataItemFlight: DataItemFlight)
    }


    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }

}