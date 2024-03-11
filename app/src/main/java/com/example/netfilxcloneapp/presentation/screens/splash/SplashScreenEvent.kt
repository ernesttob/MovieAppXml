package com.example.netfilxcloneapp.presentation.screens.splash

sealed interface SplashScreenEvent {

    data object OnNavigateToMainNavGraphAfterTenSeconds: SplashScreenAction, SplashScreenEvent
}