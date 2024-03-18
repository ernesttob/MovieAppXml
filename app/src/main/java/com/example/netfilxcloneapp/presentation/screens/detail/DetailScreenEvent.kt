package com.example.netfilxcloneapp.presentation.screens.detail

sealed interface DetailScreenEvent {

    data object OnFetchDetailMovie: DetailScreenEvent

}