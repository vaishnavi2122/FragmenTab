package com.example.fragment_tabs.users.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fragment_tabs.R
import com.example.fragment_tabs.users.model.User
import com.example.fragment_tabs.users.view_model.UserApiStatus

//Binds Glide imageView
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, avatar_url : String?){
    avatar_url?.let {
        val imgUri = avatar_url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

//Binding RecyclerView with updated Live Data
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<User>?){
    val adapter = recyclerView.adapter as UserListItemAdapter
    adapter.submitList(data)
    recyclerView.post { recyclerView.scrollToPosition(0) }
}

//Binding status for showing Loading animation before Rest APi service call
@BindingAdapter("userApiStatus")
fun bindStatus(statusImageView: ImageView, status: UserApiStatus?){
    statusImageView.apply {
        when(status){
            UserApiStatus.LOADING -> {
                visibility = View.VISIBLE
                setImageResource(R.drawable.loading_animation)
            }
            UserApiStatus.ERROR  -> {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_connection_error)
            }
            UserApiStatus.DONE -> {
                visibility = View.GONE
            }
        }
    }
}
