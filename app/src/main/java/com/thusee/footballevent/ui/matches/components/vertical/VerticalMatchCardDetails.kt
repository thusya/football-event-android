package com.thusee.footballevent.ui.matches.components.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.ui.utils.DateUtils.convertDateTime

@Composable
fun VerticalMatchCardDetails(
    match: Match,
) {
    val (formattedTime, formattedDate) = remember {
        convertDateTime(match.date)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = match.home,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            textAlign = TextAlign.Center
        )

        Column {
            Text(
                text = formattedTime,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Magenta
                ),
            )
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                ),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = match.away,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun VerticalMatchPreview() {
    VerticalMatchCardDetails(
        match = Match(
            away = "Testq",
            date = "2022-04-23T18:00:00.000Z",
            description = "Descriptiojn",
            home = "test2 ",
        )
    )
}