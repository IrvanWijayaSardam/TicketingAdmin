package com.ctwofinalproject.ticketing.admin.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctwofinalproject.ticketing.admin.model.DataUserItem

class UserAdapter(): RecyclerView.Adapter<ShowTicketAdapter.ViewHolder>() {
    private lateinit var context : Context
    private lateinit var listener: AirportAdapter.onItemClickListener


    private val diffCallback = object : DiffUtil.ItemCallback<DataUserItem>(){
        override fun areItemsTheSame(oldItem: DataUserItem, newItem: DataUserItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataUserItem, newItem: DataUserItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    fun submitList(dataUserItem: List<DataUserItem?>) = differ.submitList(dataUserItem)

    private val differ = AsyncListDiffer(this,diffCallback)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowTicketAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ShowTicketAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}