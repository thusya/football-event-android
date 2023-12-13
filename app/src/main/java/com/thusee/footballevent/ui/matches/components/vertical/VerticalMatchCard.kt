package com.thusee.footballevent.ui.matches.components.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.domain.model.Match

@Composable
fun VerticalMatchCard(
    match: Match,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp),
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    )
    {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMatchCardDetails(match)

            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.description,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewUpcoming() {
    VerticalMatchCard(
        Match(
            away = "Testq",
            date = "2022-04-23T18:00:00.000Z",
            description = "Descriptiojn",
            home = "test2 ",
        )
    )
}