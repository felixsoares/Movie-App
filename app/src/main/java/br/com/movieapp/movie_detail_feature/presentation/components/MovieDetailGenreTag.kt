package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetailGenreTag(
    genre: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primaryVariant,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = genre,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun GenreTagPreview() {
    MovieDetailGenreTag(
        genre = "Action"
    )
}