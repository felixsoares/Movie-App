package br.com.movieapp.movie_detail_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    movieId: Int? = 0,
    uiState: MovieDetailState,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit,
    navigateToMovieDetailScreen: (Int) -> Unit,
    onAddFavorite: (Movie) -> Unit,
    checkIfMovieIsFavorite: (MovieDetailEvent.CheckIsFavorite) -> Unit,
    modifier: Modifier = Modifier,
) {

    val pagingMovieSimilar = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        movieId?.let {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(it))
            checkIfMovieIsFavorite.invoke(MovieDetailEvent.CheckIsFavorite(it))
        }
    }

    MovieDetailContent(
        modifier = modifier,
        movieDetail = uiState.movieDetail,
        pagingMoviesSimilar = pagingMovieSimilar,
        isLoading = uiState.isLoading,
        isError = uiState.error,
        isFavorite = uiState.isFavorite,
        onAddFavorite = { movie ->
            onAddFavorite(movie)
        },
        navigateToMovieDetailScreen = {
            navigateToMovieDetailScreen(it)
        },
        retryError = {
            movieId?.let {
                getMovieDetail(MovieDetailEvent.GetMovieDetail(it))
            }
        }
    )
}