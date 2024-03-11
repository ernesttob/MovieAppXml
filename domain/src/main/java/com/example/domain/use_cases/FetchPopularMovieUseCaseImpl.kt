package com.example.domain.use_cases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.MovieRepository

class FetchPopularMovieUseCaseImpl(
    private val repository: MovieRepository
):FetchPopularMovieUseCase {
    override suspend fun invoke(page: Int): Result<List<MovieDomainModel>> {
        return repository.fetchPopularMovie(page = page)
    }
}