package com.example.netfilxcloneapp.presentation.screens.detail.pager.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netfilxcloneapp.R
import com.example.netfilxcloneapp.databinding.FragmentMoreDetailBinding

class MoreDetailFragment : Fragment() {

    private val binding: FragmentMoreDetailBinding by lazy {
        FragmentMoreDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}