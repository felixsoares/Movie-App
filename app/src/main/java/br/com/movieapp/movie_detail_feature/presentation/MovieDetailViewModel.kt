package br.com.movieapp.movie_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.RemoveMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun checkIfMovieIsFavorite(checkIsFavorite: MovieDetailEvent.CheckIsFavorite) {
        event(checkIsFavorite)
    }

    fun onAddFavorite(movie: Movie) {
        if (uiState.isFavorite) {
            event(MovieDetailEvent.RemoveFavorite(movie))
        } else {
            event(MovieDetailEvent.AddFavorite(movie))
        }
    }

    private fun event(movieDetailEvent: MovieDetailEvent) {
        when (movieDetailEvent) {
            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        AddMovieFavoriteUseCase.Params(movieDetailEvent.movie)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isFavorite = true
                                )
                            }

                            is ResultData.Loading -> Unit
                            is ResultData.Error -> {
                                UtilFunctions.logError(result.throwable.toString())
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    removeFavoriteUseCase.invoke(
                        RemoveMovieFavoriteUseCase.Params(movieDetailEvent.movie)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isFavorite = false
                                )
                            }

                            is ResultData.Loading -> Unit
                            is ResultData.Error -> {
                                UtilFunctions.logError(result.throwable.toString())
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.CheckIsFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        IsMovieFavoriteUseCase.Params(movieDetailEvent.movieId)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(isFavorite = result.data)
                            }

                            is ResultData.Loading -> Unit
                            is ResultData.Error -> {
                                UtilFunctions.logError(result.throwable.toString())
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailUseCase.invoke(
                        GetMovieDetailUseCase.Params(
                            movieDetailEvent.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Loading -> {
                                uiState = uiState.copy(isLoading = true)
                            }

                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = "",
                                    movieDetail = resultData.data.second,
                                    results = resultData.data.first ?: emptyFlow()
                                )
                            }

                            is ResultData.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.throwable?.message ?: "Erro desconhecido"
                                )
                                UtilFunctions.logError(resultData.throwable.toString())
                            }
                        }
                    }
                }
            }
        }
    }

}