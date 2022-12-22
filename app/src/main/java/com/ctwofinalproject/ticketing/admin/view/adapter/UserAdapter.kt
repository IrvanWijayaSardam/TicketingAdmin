package com.ctwofinalproject.ticketing.admin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctwofinalproject.ticketing.admin.R
import com.ctwofinalproject.ticketing.admin.databinding.ItemShowUserBinding
import com.ctwofinalproject.ticketing.admin.model.DataUserItem

class UserAdapter(): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context : Context
    private lateinit var listener: onItemClickListener


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

    inner class ViewHolder ( val binding : ItemShowUserBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnUpdateUser.setOnClickListener {
                listener.onItemClick(differ.currentList[adapterPosition])
            }
            binding.btnDeleteUser.setOnClickListener {
                listener.onItemDelete(differ.currentList[adapterPosition])
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(userDataItem: DataUserItem)
        fun onItemDelete(userDataItem: DataUserItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = ItemShowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtEmailUser.text = differ.currentList[position].email
        holder.binding.txtNameUser.text = differ.currentList[position].firstname
        holder.binding.txtGenderUser.text =differ.currentList[position].gender
        holder.binding.txtAddressUser.text = differ.currentList[position].address!!.homeAddress

        Glide.with(context)
            .load(differ.currentList[position].pictures)
            .error(R.drawable.img_guest)
            .circleCrop()
            .into(holder.binding.userIcon)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }
}