package com.thusee.footballevent.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thusee.footballevent.R

@Composable
fun ErrorComponent(
    message: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight()
                .padding(bottom = 12.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.error_image),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.error_title),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun ErrorStateComponentPreview() {
    ErrorComponent(
        message = "Please try again later",
    )
}