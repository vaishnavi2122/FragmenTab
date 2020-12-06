package com.example.fragment_tabs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_tabs.base.BaseBottomTabFragment
import com.example.fragment_tabs.databinding.FragmentSearchBinding

class SearchFragment : BaseBottomTabFragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentSearchBinding.inflate(inflater)
        return binding.root
    }
}