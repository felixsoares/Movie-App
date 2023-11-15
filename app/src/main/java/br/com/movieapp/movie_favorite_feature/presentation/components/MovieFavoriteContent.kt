package br.com.movieapp.movie_favorite_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie

@Composable
fun MovieFavoriteContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie>,
    onClick: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center
    ) {
        if (movies.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_favorite_movie),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(
                        items = movies,
                        key = { movie -> movie.id }
                    ) { movie ->
                        MovieFavoriteItem(
                            movie = movie,
                            onClick = onClick
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MovieFavoriteContentPreview() {
    MovieFavoriteContent(
        modifier = Modifier,
        paddingValues = PaddingValues(),
        movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                imageUrl = "Poster Path 1",
                voteAverage = 1.0,
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                imageUrl = "Poster Path 2",
                voteAverage = 2.0,
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                imageUrl = "Poster Path 3",
                voteAverage = 3.0,
            )
        ),
        onClick = {}
    )
}

@Preview
@Composable
fun MovieFavoriteContentPreview2() {
    MovieFavoriteContent(
        modifier = Modifier,
        paddingValues = PaddingValues(),
        movies = emptyList(),
        onClick = {}
    )
}