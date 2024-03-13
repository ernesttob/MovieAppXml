package com.example.netfilxcloneapp.di

import com.example.domain.repository.MovieRepository
import com.example.domain.use_cases.details.FetchDetailMovieUseCase
import com.example.domain.use_cases.details.FetchDetailMovieUseCaseImpl
import com.example.domain.use_cases.now_playing.FetchNowPlayingMovieUseCase
import com.example.domain.use_cases.now_playing.FetchNowPlayingMovieUseCaseImpl
import com.example.domain.use_cases.popular.FetchPopularMovieUseCase
import com.example.domain.use_cases.popular.FetchPopularMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesFetchPopularMovieUseCase(
        repository: MovieRepository
    ): FetchPopularMovieUseCase = FetchPopularMovieUseCaseImpl(repository)

    @Provides
    fun providesFetchNowPlayingMovieUseCase(
        repository: MovieRepository
    ): FetchNowPlayingMovieUseCase = FetchNowPlayingMovieUseCaseImpl(repository)

    @Provides
    fun providesFetchDetailsMovieUseCase(
        repository: MovieRepository
    ): FetchDetailMovieUseCase = FetchDetailMovieUseCaseImpl(repository)
}