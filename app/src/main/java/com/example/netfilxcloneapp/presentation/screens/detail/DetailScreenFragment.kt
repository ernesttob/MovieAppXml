package com.example.netfilxcloneapp.presentation.screens.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.netfilxcloneapp.R
import com.example.netfilxcloneapp.databinding.FragmentDetailScreenBinding
import com.example.netfilxcloneapp.presentation.screens.detail.DetailScreenAction.FetchDetailMovie
import com.example.netfilxcloneapp.presentation.screens.home.HomeScreenFragment.Companion.DETAIL_ID_ARG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private val binding: FragmentDetailScreenBinding by lazy {
        FragmentDetailScreenBinding.inflate(layoutInflater)
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
        movieId?.let {
            viewModel.onEvent(
                event = DetailScreenEvent.OnFetchDetailMovie,
                movieId = it
            )
        }
    }

    private fun fetchDetailMovie(action: DetailScreenAction.FetchDetailMovie) {
        Glide
            .with(view!!.context)
            .load(action.detailMovie.posterPath)
            .into(binding.moviePosterForDetail)
    }

}