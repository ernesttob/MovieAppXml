package com.example.netfilxcloneapp.presentation.screens.detail.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netfilxcloneapp.R
import com.example.netfilxcloneapp.databinding.FragmentCastMovieBinding

class CastMovieFragment : Fragment() {

    private val binding: FragmentCastMovieBinding by lazy {
        FragmentCastMovieBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}