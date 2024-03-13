package com.example.netfilxcloneapp.presentation.screens.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.netfilxcloneapp.databinding.FragmentCastMovieBinding
import com.example.netfilxcloneapp.databinding.FragmentDetailScreenBinding
import com.example.netfilxcloneapp.databinding.FragmentMoreMovieBinding
import com.example.netfilxcloneapp.databinding.FragmentTrailerMovieBinding
import com.example.netfilxcloneapp.presentation.screens.detail.DetailScreenAction.FetchDetailMovie
import com.example.netfilxcloneapp.presentation.screens.detail.pager.PagerAdapter
import com.example.netfilxcloneapp.presentation.screens.home.HomeScreenFragment.Companion.DETAIL_ID_ARG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private val binding: FragmentDetailScreenBinding by lazy {
        FragmentDetailScreenBinding.inflate(layoutInflater)
    }

    private val trailer: FragmentTrailerMovieBinding by lazy {
        FragmentTrailerMovieBinding.inflate(layoutInflater)
    }

    private val cast: FragmentCastMovieBinding by lazy {
        FragmentCastMovieBinding.inflate(layoutInflater)
    }

    private val more: FragmentMoreMovieBinding by lazy {
        FragmentMoreMovieBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieIdArg = arguments?.getInt(DETAIL_ID_ARG)
        sendUiEvents(movieIdArg)
        setupDataListeners()
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

    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(Color.TRANSPARENT, Color.BLACK)
    )


    @SuppressLint("ResourceAsColor")
    private fun fetchDetailMovie(action: FetchDetailMovie) {
        Glide
            .with(requireContext())
            .load(action.detailMovie.posterPath)
            .into(binding.moviePosterForDetail)
        binding.backIcon.setOnClickListener {
            requireView().findNavController().popBackStack()
        }
        binding.moviePosterForDetail.background = gradientDrawable
        binding.moviePosterForDetail.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        binding.title.text = action.detailMovie.title
        binding.movieDescription.text = action.detailMovie.overview
        val pagerAdapter = PagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.horizontalPager.adapter = pagerAdapter
        binding.horizontalPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
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
        }
        )
    }
}