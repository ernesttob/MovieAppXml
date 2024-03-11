package com.example.netfilxcloneapp.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.netfilxcloneapp.databinding.FragmentHomeScreenBinding
import com.example.netfilxcloneapp.presentation.screens.home.adapter.MoviesItemAdapter
import com.example.netfilxcloneapp.presentation.utils.PeekingLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private val binding: FragmentHomeScreenBinding by lazy {
        FragmentHomeScreenBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeScreenViewModel by viewModels()

    private val adapter: MoviesItemAdapter by lazy {
        MoviesItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        sendUiEvents()
        setupDataListeners()
    }

    private fun setUpUi() = with(binding){
        moviesRv.layoutManager = PeekingLinearLayoutManager(requireContext())
        moviesRv.adapter = adapter
    }

    private fun sendUiEvents() {
        viewModel.onEvent(HomeScreenEvent.OnFetchPopularMovies)
    }

    private fun setupDataListeners() {
        lifecycleScope.launch {
            viewModel.uiAction.collectLatest { action ->
                when (action) {
                    is HomeScreenAction.FetchPopularMovies -> adapter.submitList(action.popularMovies)
                }
            }
        }
    }
}