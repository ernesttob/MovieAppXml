package com.example.domain.repository

import com.example.domain.models.movie_details_domain.MovieDetailModelDomain
import com.example.domain.models.movie_details_domain.movie_cast.MovieCastModelDomain
import com.example.domain.models.movie_list_domain.MovieDomainModel
import com.example.domain.models.person_models_domain.PersonModelsDomain
import com.example.domain.models.person_models_domain.ResultDomain

interface MovieRepository {

    suspend fun fetchCastDetailMovie(
        movieId: Int
    ): Result<MovieCastModelDomain>

    suspend fun fetchTopRatedMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

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

    suspend fun fetchPopularActors(
        page: Int
    ): Result<List<ResultDomain>>

}