package com.example.netfilxcloneapp.presentation.screens.person

sealed interface PersonEvent {

    data object OnFetchPopularPerson: PersonEvent

}