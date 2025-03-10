package br.com.movieapp.core.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().height(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
        )
        Button(onClick = retry) {
            Text(text = "Recarregar", style = TextStyle(color = Color.White))
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    MovieErrorItem(
        message = "Error",
        retry = {}
    )
}