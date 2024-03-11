package com.example.data.repository.impl

import com.example.data.mappers.toDomainModel
import com.example.data.models.MovieResponseCloudModel
import com.example.data.remote.MovieService
import com.example.domain.models.MovieDomainModel
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