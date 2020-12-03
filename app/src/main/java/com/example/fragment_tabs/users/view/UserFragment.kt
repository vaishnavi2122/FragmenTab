package com.example.fragment_tabs.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragment_tabs.databinding.FragmentUserBinding
import com.example.fragment_tabs.users.adapter.UserListItemAdapter
import com.example.fragment_tabs.users.view_model.UserViewModel

class UserFragment : Fragment() {
    private lateinit var binding : FragmentUserBinding

    //Lazily initialize UserViewModel
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the UserFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the UserViewModel
        binding.userViewModel = viewModel

        //Adapter for RecyclerView
        binding.recyclerView.adapter = UserListItemAdapter()

        return binding.root
    }
}