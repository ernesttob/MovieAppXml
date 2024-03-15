package com.example.netfilxcloneapp.presentation.screens.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.netfilxcloneapp.databinding.FragmentCastDetailBinding
import com.example.netfilxcloneapp.databinding.FragmentDetailScreenBinding
import com.example.netfilxcloneapp.databinding.FragmentMoreDetailBinding
import com.example.netfilxcloneapp.databinding.FragmentTrailerDetailBinding
import com.example.netfilxcloneapp.presentation.screens.detail.DetailScreenAction.FetchDetailMovie
import com.example.netfilxcloneapp.presentation.screens.detail.pager.cast.CastDetailFragment
import com.example.netfilxcloneapp.presentation.screens.detail.pager.more.MoreDetailFragment
import com.example.netfilxcloneapp.presentation.screens.detail.pager.trailer.TrailerDetailFragment
import com.example.netfilxcloneapp.presentation.screens.detail.pager.adapter.ViewPagerAdapter
import com.example.netfilxcloneapp.presentation.screens.home.HomeScreenFragment.Companion.DETAIL_ID_ARG
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private val binding: FragmentDetailScreenBinding by lazy {
        FragmentDetailScreenBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailScreenViewModel by viewModels()

    private val trailer: FragmentTrailerDetailBinding by lazy {
        FragmentTrailerDetailBinding.inflate(layoutInflater)
    }

    private val cast: FragmentCastDetailBinding by lazy {
        FragmentCastDetailBinding.inflate(layoutInflater)
    }

    private val more: FragmentMoreDetailBinding by lazy {
        FragmentMoreDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieIdArg = arguments?.getInt(DETAIL_ID_ARG)
        sendUiEvents(movieIdArg)
        setupDataListeners()
        horizontalPager()
        Log.d("TTT","$movieIdArg")
    }

    private fun setupDataListeners() {
        lifecycleScope.launch {
            viewModel.uiAction.collectLatest { action ->
                when (action) {
                    is FetchDetailMovie -> fetchDetailMovie(action)
                }
            }
        }
    }

    private fun sendUiEvents(movieId: Int?) {
        if (movieId != null) {
            viewModel.onEvent(
                event = DetailScreenEvent.OnFetchDetailMovie,
                movieId = movieId
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun fetchDetailMovie(action: FetchDetailMovie) {
        bundleOf(CAST_ID_ARG to action.detailMovie.id)
        Glide
            .with(requireContext())
            .load(action.detailMovie.posterPath)
            .into(binding.moviePosterForDetail)
        binding.moviePosterForDetail.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        binding.title.text = action.detailMovie.title
        binding.movieDescription.text = action.detailMovie.overview
    }

    private fun horizontalPager() {
        val tabLayout: TabLayout = binding.tabLayoutDetailScreen.tabLayoutDetail
        val viewPager: ViewPager2 = binding.tabLayoutDetailScreen.viewPager
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        adapter.addFragment(TrailerDetailFragment(), "Trailer")
        adapter.addFragment(CastDetailFragment(), "Cast")
        adapter.addFragment(MoreDetailFragment(), "More")
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        trailer
                    }
                    1 -> {
                        cast
                    }
                    2 -> {
                        more
                    }
                }
            }
        })
    }
    companion object {
        const val CAST_ID_ARG = "CAST_ID_ARG"
    }
}