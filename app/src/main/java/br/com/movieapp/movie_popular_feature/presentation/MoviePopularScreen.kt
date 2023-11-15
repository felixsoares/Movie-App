package br.com.movieapp.movie_popular_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.movie_popular_feature.presentation.components.MovieContent
import br.com.movieapp.movie_popular_feature.presentation.state.MoviePopularState

@Composable
fun MoviePopularScreen(
    modifier: Modifier = Modifier,
    uiState: MoviePopularState,
    navigateToMovieDetailScreen: (id: Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    MovieContent(
        modifier = modifier,
        pagingMovies = movies,
        navigateToMovieDetailScreen = { id ->
            navigateToMovieDetailScreen(id)
        }
    )

}