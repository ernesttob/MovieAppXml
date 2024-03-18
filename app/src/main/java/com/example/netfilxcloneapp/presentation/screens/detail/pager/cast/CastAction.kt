package com.example.netfilxcloneapp.presentation.screens.detail.pager.cast

import com.example.domain.models.movie_details_domain.movie_cast.CastDomain
import com.example.domain.models.movie_details_domain.movie_cast.MovieCastModelDomain
import java.io.Serializable

sealed interface CastAction {

    data class FetchCastDetail(
        val castDetail: MovieCastModelDomain
    ): CastAction
}