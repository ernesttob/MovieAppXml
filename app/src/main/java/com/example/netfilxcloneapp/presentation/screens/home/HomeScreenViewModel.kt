package com.example.netfilxcloneapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.FetchPopularMovieUseCase
import com.example.domain.use_cases.FetchPopularMovieUseCaseImpl
import com.example.netfilxcloneapp.presentation.screens.splash.SplashScreenAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchPopularMoviesUseCase: FetchPopularMovieUseCase
):ViewModel() {

    private val _uiAction: MutableSharedFlow<HomeScreenAction> = MutableSharedFlow()
    val uiAction: SharedFlow<HomeScreenAction> = _uiAction.asSharedFlow()

    fun onEvent(event: HomeScreenEvent){
        when(event) {
            HomeScreenEvent.OnFetchPopularMovies -> onFetchPopularMovies()
        }
    }

    private fun onFetchPopularMovies(){
        viewModelScope.launch {
            val response = fetchPopularMoviesUseCase(page = 1)
            if (response.isSuccess){
                _uiAction.emit(
                    HomeScreenAction.FetchPopularMovies(
                        popularMovies = response.getOrNull().orEmpty()
                    )
                )
            }
        }
    }

}