package com.example.netfilxcloneapp.presentation.screens.detail.pager.cast

sealed interface CastEvent {
    data object OnFetchCastDetails:CastEvent
}