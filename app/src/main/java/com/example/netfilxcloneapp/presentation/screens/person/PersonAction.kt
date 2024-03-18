package com.example.netfilxcloneapp.presentation.screens.person

import com.example.domain.models.person_models_domain.ResultDomain

sealed interface PersonAction {

    data class FetchPopularPerson(
        val popularPersons: List<ResultDomain>
    ): PersonAction

}