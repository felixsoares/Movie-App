package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetail
import br.com.movieapp.core.util.toBackDropUrl
import br.com.movieapp.movie_detail_feature.data.mapper.toMovie
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail?,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onAddFavorite: (Movie) -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieDetailBackDrop(
            imageUrl = movieDetail?.backdropPathUrl?.toBackDropUrl() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    movieDetail?.let { onAddFavorite(it.toMovie()) }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else MaterialTheme.colors.primaryVariant,
                )
            }
        }
        Text(
            text = movieDetail?.title ?: "",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            mainAxisSpacing = 10.dp,
            mainAxisAlignment = MainAxisAlignment.Center,
            crossAxisSpacing = 10.dp,
        ) {
            movieDetail?.genres?.forEach { genre ->
                MovieDetailGenreTag(genre = genre)
            }
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        MovieInfoContent(
            movieDetail = movieDetail,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        RatingBar(
            rating = (movieDetail?.voteAverage?.toFloat()?.div(2f)) ?: 0f,
            modifier = Modifier.height(15.dp)
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        val overview = movieDetail?.overview ?: ""
        if (overview.isNotEmpty()) {
            MovieDetailDescription(
                description = movieDetail?.overview ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
    }
}

@Preview
@Composable
fun MovieDetailHeaderPreview() {
    MovieDetailHeader(
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
        isFavorite = false,
        onAddFavorite = {}
    )
}