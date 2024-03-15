package com.example.domain.repository

import com.example.domain.models.movie_details_domain.MovieDetailModelDomain
import com.example.domain.models.movie_details_domain.movie_cast.MovieCastModelDomain
import com.example.domain.models.movie_list_domain.MovieDomainModel

interface MovieRepository {

    suspend fun fetchCastDetailMovie(
        movieId: Int
    ): Result<MovieCastModelDomain>

    suspend fun fetchPopularMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

    suspend fun fetchUpcomingMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

    suspend fun fetchNowPlayingMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

    suspend fun fetchDetailMovie(
        movieId: Int
    ): Result<MovieDetailModelDomain>

}