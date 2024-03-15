package com.example.netfilxcloneapp.presentation.screens.detail.pager.trailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netfilxcloneapp.R
import com.example.netfilxcloneapp.databinding.FragmentCastDetailBinding
import com.example.netfilxcloneapp.databinding.FragmentTrailerDetailBinding


class TrailerDetailFragment : Fragment() {

    private val binding: FragmentTrailerDetailBinding by lazy {
        FragmentTrailerDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}