package com.example.fragment_tabs.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_tabs.databinding.UserListItemBinding
import com.example.fragment_tabs.users.model.User

class UserListItemAdapter : ListAdapter<User, UserListItemAdapter.UserListItemViewHolder>(DiffCallback){
    class UserListItemViewHolder(private var binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){
        //Binding data to the view
        fun bind(user : User){
            binding.user = user
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        val item = getItem(position)
        //Calling binder to set the current view
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
}