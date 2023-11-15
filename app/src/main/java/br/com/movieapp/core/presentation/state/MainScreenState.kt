package br.com.movieapp.core.presentation.state

data class MainScreenState(
    val isBottomBarVisible: Boolean = true,
    val titleName: Int = 0,
    val hasBackArrow: Boolean = false
)