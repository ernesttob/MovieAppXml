package com.example.domain.use_cases

import com.example.domain.models.MovieDomainModel

interface FetchPopularMovieUseCase {
    suspend operator fun invoke(
        page:Int
    ):Result<List<MovieDomainModel>>
}