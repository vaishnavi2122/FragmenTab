package com.example.fragment_tabs.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_tabs.databinding.UserListItemBinding
import com.example.fragment_tabs.users.model.User

//OnListItemClick this listener gets called from the layout binding
class UserListItemListener(val clickListener : (user : User) -> Unit){
    fun onClick(user: User) = clickListener(user)
}

class UserListItemAdapter(private val clickListener : UserListItemListener) : ListAdapter<User, UserListItemAdapter.UserListItemViewHolder>(DiffCallback){
    class UserListItemViewHolder(private var binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){
        //Binding data to the view
        fun bind(user: User, clickListener: UserListItemListener){
            binding.user = user
            binding.executePendingBindings()

            //Setting click listener to binding
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        val item = getItem(position)
        //Calling binder to set the current view wit click listener for item click
        holder.bind(item, clickListener)
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