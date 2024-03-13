package com.example.data.repository.impl

import com.example.data.mappers.toDomainModel
import com.example.data.models.movie_details.MovieDetailModel
import com.example.data.models.movie_list.MovieResponseCloudModel
import com.example.data.remote.MovieService
import com.example.domain.models.movie_details_domain.MovieDetailModelDomain
import com.example.domain.models.movie_list_domain.MovieDomainModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.CancellationException
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {

    override suspend fun fetchPopularMovie(
        page: Int
    ): Result<List<MovieDomainModel>> =
        sendRequestService(model = service.getPopularMovies(page = page))

    override suspend fun fetchUpcomingMovie(
        page: Int
    ): Result<List<MovieDomainModel>> =
        sendRequestService(model = service.getUpcomingMovies(page = page))

    override suspend fun fetchNowPlayingMovie(
        page: Int
    ): Result<List<MovieDomainModel>> =
        sendRequestService( model = service.getNowPlayingMovies(page = page))

    override suspend fun fetchDetailMovie(movieId: Int): Result<MovieDetailModelDomain> {
        return try {
            val response = service.getMovieDetails(movieId)
            if (response.isSuccessful) {
                val movieDetail = response.body()?.toDomainModel() ?: throw NullPointerException("Movie details are null")
                Result.success(movieDetail)
            } else {
                Result.failure(Throwable("Failed to fetch movie details"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun sendRequestService(model: Response<MovieResponseCloudModel>): Result<List<MovieDomainModel>> {
        return try {
            if (model.isSuccessful) {
                return Result.success(
                    model.body()?.movies?.map {
                        it.toDomainModel()
                    }.orEmpty()
                )
            } else Result.failure(Throwable())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}
