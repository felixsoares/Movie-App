package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R

@Composable
fun MovieDetailDescription(
    description: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
        )
        Text(
            text = if (!expanded)
                stringResource(id = R.string.read_more)
            else
                stringResource(id = R.string.read_less),
            style = TextStyle(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded }
        )
    }
}

@Preview
@Composable
fun MovieDescriptionPreview() {
    MovieDetailDescription(description = "sabdhas asgdags hdghsa gdhaghd gashgdhasg dhas sabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhassabdhas asgdags hdghsa gdhaghd gashgdhasg dhas")
}