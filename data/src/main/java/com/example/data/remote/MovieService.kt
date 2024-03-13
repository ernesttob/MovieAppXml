package com.example.data.remote

import com.example.data.models.movie_details.MovieDetailModel
import com.example.data.models.movie_list.MovieResponseCloudModel
import com.example.data.utils.Constants.API_KEY
import com.example.data.utils.Constants.LANGUAGE_RU
import com.example.data.utils.Constants.Movie.MOVIE_DETAILS
import com.example.data.utils.Constants.Movie.NOW_PLAYING
import com.example.data.utils.Constants.Movie.POPULAR
import com.example.data.utils.Constants.Movie.RECOMMENDATIONS
import com.example.data.utils.Constants.Movie.SIMILAR
import com.example.data.utils.Constants.Movie.TOP_RATED
import com.example.data.utils.Constants.Movie.UPCOMING
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = LANGUAGE_RU,
    ): Response<MovieResponseCloudModel>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int,
    ): Response<MovieResponseCloudModel>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int?,
    ): Response<MovieResponseCloudModel>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int?,
    ): Response<MovieResponseCloudModel>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_RU,
        @Query("page") page: Int? = 1,
    ): Response<MovieDetailModel>

    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_RU,
    ): Response<MovieResponseCloudModel>

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_RU,
    ): Response<MovieResponseCloudModel>
}