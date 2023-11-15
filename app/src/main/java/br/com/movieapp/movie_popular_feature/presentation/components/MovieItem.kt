package br.com.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun MovieItem(
    voteAverage: Double,
    imageUrl: String,
    id: Int,
    modifier: Modifier = Modifier,
    navigateToMovieDetailScreen: (id: Int) -> Unit
) {
    Box(modifier = modifier) {
        MovieRate(
            rate = voteAverage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 5.dp, bottom = 5.dp)
        )
        MoviePoster(
            imageUrl = imageUrl,
            id = id,
            onClick = navigateToMovieDetailScreen
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        voteAverage = 10.0,
        imageUrl = "",
        id = 1,
        modifier = Modifier
    ) {}
}