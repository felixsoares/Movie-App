package br.com.movieapp.movie_search_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.movie_search_feature.data.usecase.GetMovieSearchUseCase
import br.com.movieapp.movie_search_feature.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUseCase: GetMovieSearchUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = "") {
        viewModelScope.launch {
            val movies = getMovieSearchUseCase.invoke(
                GetMovieSearchUseCase.Params(query)
            ).cachedIn(viewModelScope)

            uiState = uiState.copy(
                movies = movies
            )
        }
    }

    fun event(event: MovieSearchEvent) {
        uiState = when (event) {
            is MovieSearchEvent.EnteredQuery -> uiState.copy(query = event.query)
        }
    }

}