package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.core.presentation.components.common.MovieErrorItem
import br.com.movieapp.core.presentation.components.common.MovieLoadingItem
import br.com.movieapp.core.util.toPostUrl
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail?,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isError: String,
    isFavorite: Boolean,
    navigateToMovieDetailScreen: (Int) -> Unit,
    onAddFavorite: (Movie) -> Unit,
    retryError: () -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
    ) {
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            MovieDetailHeader(
                movieDetail = movieDetail,
                isFavorite = isFavorite,
                onAddFavorite = onAddFavorite
            )
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (isError.isNotEmpty() && !isLoading) {
                    MovieErrorItem(
                        message = isError,
                        modifier = Modifier.padding(start = 8.dp, end = 0.dp)
                    ) {
                        retryError()
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            if (pagingMoviesSimilar.itemCount > 0) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.movies_similar),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.primaryVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(alignment = Alignment.Start)
                    )
                }
            }
        }

        items(pagingMoviesSimilar.itemCount) { position ->
            val movie = pagingMoviesSimilar[position]
            movie?.let {
                MovieItem(
                    voteAverage = it.voteAverage,
                    imageUrl = it.imageUrl.toPostUrl(),
                    id = it.id,
                    navigateToMovieDetailScreen = navigateToMovieDetailScreen
                )
            }
        }

        pagingMoviesSimilar.apply {
            when {

                loadState.append is LoadState.Loading -> {
                    item {
                        MovieLoadingItem(modifier = Modifier)
                    }
                }

                loadState.refresh is LoadState.Error -> {
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

@Preview
@Composable
fun MovieDetailContentPreview() {
    MovieDetailContent(
        movieDetail = MovieDetail(
            id = 1,
            title = "Movie Title",
            genres = listOf("nome1", "nome2"),
            overview = "Movie Overvied asdasd asdu sadashuhsauhdhs u duashud hsauw",
            releaseDate = "2021-01-01",
            backdropPathUrl = "",
            voteAverage = 10.0,
            duration = 120
        ),
        pagingMoviesSimilar = flowOf(PagingData.from(emptyList<Movie>())).collectAsLazyPagingItems(),
        isLoading = true,
        isError = "ashuahsua",
        isFavorite = false,
        onAddFavorite = {},
        navigateToMovieDetailScreen = {},
        retryError = {}
    )
}