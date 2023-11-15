package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.MovieDetail

@Composable
fun MovieInfoContent(
    movieDetail: MovieDetail?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieInfo(
            name = stringResource(id = R.string.vote_average),
            value = movieDetail?.voteAverage.toString()
        )
        MovieInfo(
            name = stringResource(id = R.string.duration),
            value = stringResource(id = R.string.duration_minutes, movieDetail?.duration.toString())
        )
        MovieInfo(
            name = stringResource(id = R.string.release_date),
            value = movieDetail?.releaseDate.toString()
        )
    }
}


@Composable
fun MovieInfo(
    name: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun MovieInfoContentPreview() {
    MovieInfoContent(
        movieDetail = MovieDetail(
            id = 1,
            title = "Movie Title",
            genres = listOf(),
            overview = "Movie Overview",
            releaseDate = "2021-01-01",
            backdropPathUrl = "",
            voteAverage = 10.0,
            duration = 120,
            voteCount = 120
        )
    )
}