package com.example.netfilxcloneapp.presentation.screens.detail.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrailerMovieFragment()
            1 -> CastMovieFragment()
            2 -> MoreMovieFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}