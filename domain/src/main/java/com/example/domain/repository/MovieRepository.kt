package com.example.domain.repository

import com.example.domain.models.MovieDomainModel

interface MovieRepository {

    suspend fun fetchPopularMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

    suspend fun fetchUpcomingMovie(
        page: Int
    ): Result<List<MovieDomainModel>>

}