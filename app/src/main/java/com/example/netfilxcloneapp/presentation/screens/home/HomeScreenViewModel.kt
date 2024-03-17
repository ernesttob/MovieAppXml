package com.example.netfilxcloneapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.now_playing.FetchNowPlayingMovieUseCase
import com.example.domain.use_cases.popular.FetchPopularMovieUseCase
import com.example.domain.use_cases.top_rated.FetchTopRatedMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchPopularMoviesUseCase: FetchPopularMovieUseCase,
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMovieUseCase,
    private val fetchTopRatedMovieUseCase: FetchTopRatedMovieUseCase
) : ViewModel() {

    private val _uiAction: MutableSharedFlow<HomeScreenAction> = MutableSharedFlow()
    val uiAction: SharedFlow<HomeScreenAction> = _uiAction.asSharedFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnFetchAllMovies -> onFetchAllMovies()
            is HomeScreenEvent.OnNavigateToDetails -> onNavigateToDetailsScreen(event)
        }
    }

    private fun onNavigateToDetailsScreen(event: HomeScreenEvent.OnNavigateToDetails){
        viewModelScope.launch {
            _uiAction.emit(HomeScreenAction.NavigateToDetailsScreen(event.movieId))
        }
    }

    private fun onFetchAllMovies() {
        viewModelScope.launch {
            val popularResponseDeferred = async {
                fetchPopularMoviesUseCase(page = 1)
            }
            val nowPLayingResponseDeferred = async {
                fetchNowPlayingMoviesUseCase(page = 1)
            }
            val topRatedResponseDeferred = async {
                fetchTopRatedMovieUseCase(page = 1)
            }

            val topRatedMovies = topRatedResponseDeferred.await()
            val popularMovies = popularResponseDeferred.await()
            val nowPlayingMovies = nowPLayingResponseDeferred.await()

            if (popularMovies.isSuccess && nowPlayingMovies.isSuccess && topRatedMovies.isSuccess) {
                _uiAction.emit(
                    HomeScreenAction.FetchAllMovies(
                        popularMovies = popularMovies.getOrNull().orEmpty(),
                        nowPlayingMovies = nowPlayingMovies.getOrNull().orEmpty(),
                        topRatedMovies = topRatedMovies.getOrNull().orEmpty()
                    )
                )
                _uiAction.emit(HomeScreenAction.UpdateScreenHideShimmer)
            }
        }
    }
}