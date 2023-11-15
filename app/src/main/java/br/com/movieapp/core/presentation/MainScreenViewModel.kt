package br.com.movieapp.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.movieapp.core.presentation.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(MainScreenState())
        private set

    fun setPageTitle(title: Int) {
        uiState = uiState.copy(titleName = title)
    }

    fun setBottomBarVisibility(isVisible: Boolean) {
        uiState = uiState.copy(isBottomBarVisible = isVisible)
    }

    fun setBackArrowEnable(isEnable:Boolean){
        uiState = uiState.copy(hasBackArrow = isEnable)
    }

}