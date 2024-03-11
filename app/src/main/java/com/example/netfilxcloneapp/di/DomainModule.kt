package com.example.netfilxcloneapp.di

import com.example.domain.repository.MovieRepository
import com.example.domain.use_cases.FetchPopularMovieUseCase
import com.example.domain.use_cases.FetchPopularMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesFetchPopularMovieUseCase(
        repository: MovieRepository
    ): FetchPopularMovieUseCase = FetchPopularMovieUseCaseImpl(repository)
}