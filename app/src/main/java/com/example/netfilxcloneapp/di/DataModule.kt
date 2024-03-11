package com.example.netfilxcloneapp.di

import com.example.data.remote.MovieService
import com.example.data.repository.impl.MovieRepositoryImpl
import com.example.data.utils.Constants.API_KEY
import com.example.data.utils.Constants.BASE_URL
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMovieRepository(
        movieService: MovieService
    ): MovieRepository = MovieRepositoryImpl(movieService)


}