package br.com.movieapp.movie_search_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.presentation.components.common.MovieErrorItem
import br.com.movieapp.core.presentation.components.common.MovieLoadingItem
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem
import br.com.movieapp.movie_search_feature.presentation.MovieSearchEvent
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieSearchContent(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearchMovies: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    navigateToMovieDetailScreen: (Int) -> Unit,
) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearchMovies(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        modifier = Modifier,
                        navigateToMovieDetailScreen = { id ->
                            navigateToMovieDetailScreen(id)
                        }
                    )
                }
                isLoading = false
            }

            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            MovieLoadingItem(modifier = Modifier)
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            MovieErrorItem(modifier = Modifier, "Error") {
                                retry()
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        isLoading = false
                        item {
                            MovieErrorItem(modifier = Modifier, "Error") {
                                retry()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieSearchContentPreview() {
    MovieSearchContent(
        pagingMovies = flowOf(PagingData.from(emptyList<MovieSearch>())).collectAsLazyPagingItems(),
        query = "",
        onSearchMovies = {},
        onEvent = {},
        navigateToMovieDetailScreen = {}
    )
}