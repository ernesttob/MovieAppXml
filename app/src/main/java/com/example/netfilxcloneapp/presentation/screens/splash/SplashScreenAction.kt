package com.example.netfilxcloneapp.presentation.screens.splash

sealed interface SplashScreenAction {
    data object NavigateToMainGraph: SplashScreenAction
}