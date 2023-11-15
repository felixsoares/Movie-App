package br.com.movieapp.movie_search_feature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.movie_search_feature.presentation.components.MovieSearchContent
import br.com.movieapp.movie_search_feature.presentation.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToMovieDetailScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    MovieSearchContent(
        pagingMovies = pagingMovies,
        query = uiState.query,
        onSearchMovies = { onFetch(it) },
        onEvent = { onEvent(it) },
        navigateToMovieDetailScreen = { navigateToMovieDetailScreen(it) },
        modifier = modifier
    )
}