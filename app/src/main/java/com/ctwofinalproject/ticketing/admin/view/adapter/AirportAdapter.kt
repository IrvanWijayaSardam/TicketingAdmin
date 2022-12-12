package com.ctwofinalproject.ticketing.admin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctwofinalproject.ticketing.admin.databinding.ItemChooseAllAirportBinding
import com.ctwofinalproject.ticketing.admin.model.DataItem

class AirportAdapter(): RecyclerView.Adapter<AirportAdapter.ViewHolder>() {
    private lateinit var context : Context
    private lateinit var listener: onItemClickListener

    private val diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(dataitem : List<DataItem>?) = differ.submitList(dataitem)

    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }

    interface onItemClickListener {
        fun onItemClick(airportData: DataItem)
    }

    inner class ViewHolder ( val binding : ItemChooseAllAirportBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(differ.currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemChooseAllAirportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCityCountryItemChooseAllAirport.setText(differ.currentList[position].city + "," + differ.currentList[position].country)
        holder.binding.tvAirportItemChooseAllAirport.text = differ.currentList[position].name
        holder.binding.tvAirportCodeItemChooseAirport.text = differ.currentList[position].code
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}