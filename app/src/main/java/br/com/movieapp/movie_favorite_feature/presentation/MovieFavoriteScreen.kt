package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import br.com.movieapp.movie_favorite_feature.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetail: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val movies = uiState.movies

    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            MovieFavoriteContent(
                modifier = Modifier,
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToDetail(movieId)
                }
            )
        }
    )
}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        uiState = MovieFavoriteState(
            listOf(
                Movie(1, "", 1.0, "")
            )
        ),
        navigateToDetail = {}
    )
}