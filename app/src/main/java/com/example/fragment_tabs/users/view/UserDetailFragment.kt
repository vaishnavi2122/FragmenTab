package com.example.fragment_tabs.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_tabs.R
import com.example.fragment_tabs.databinding.FragmentUserDetailBinding
import kotlinx.android.synthetic.main.activity_main.*

class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  FragmentUserDetailBinding.inflate(inflater)

        //Getting arguments
        val args = UserDetailFragmentArgs.fromBundle(requireArguments())
        binding.userName.text = args.userName

        return binding.root
    }

    /*For making bottom navigation tab active on UserDetailPage
    when traversing back stack*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Getting bottom navigation view reference
        val bottomView = requireActivity().bottom_navigation_view
        bottomView.menu.findItem(R.id.user_fragment).isChecked = true
    }
}