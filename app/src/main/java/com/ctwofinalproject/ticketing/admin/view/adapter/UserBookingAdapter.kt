package com.ctwofinalproject.ticketing.admin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ctwofinalproject.ticketing.admin.databinding.ItemListUserBookingBinding
import com.ctwofinalproject.ticketing.admin.model.DataItemFlight
import com.ctwofinalproject.ticketing.admin.model.DataItemListBooking

class UserBookingAdapter() : RecyclerView.Adapter<UserBookingAdapter.ViewHolder>() {
    private lateinit var listener : onItemClickListener

    private val diffCallback = object : DiffUtil.ItemCallback<DataItemListBooking>(){
        override fun areItemsTheSame(oldItem: DataItemListBooking, newItem: DataItemListBooking): Boolean {
            return oldItem.bookingId == newItem.bookingId
        }

        override fun areContentsTheSame(oldItem: DataItemListBooking, newItem: DataItemListBooking): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    fun submitList(dataItemListBooking: List<DataItemListBooking?>) = differ.submitList(dataItemListBooking)

    private val differ = AsyncListDiffer(this,diffCallback)

    inner class ViewHolder(val binding : ItemListUserBookingBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cvItemListUserBooking.setOnClickListener {
                listener.onItemClick(differ.currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListUserBookingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvBookingIdItemListBook.setText("Booking : "+differ.currentList[position].bookingId.toString())
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    interface onItemClickListener{
        fun onItemClick(dataItemListBooking: DataItemListBooking)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }
}