package com.thusee.footballevent.ui.components.horizontal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.ui.utils.DateUtils

@Composable
fun HorizontalMatchCard(
    match: Match,
    modifier: Modifier = Modifier
) {
    val (formattedTime, formattedDate) = remember {
        DateUtils.convertDateTime(match.date)
    }
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Column {
                    Text(
                        text = match.home,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight(600)
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.vs),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight(700)
                        ),
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = match.away,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight(600)
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .width(1.dp)
                )
                Spacer(modifier = Modifier.width(32.dp))
                Column {
                    Text(
                        text = formattedDate,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp,
                            fontStyle = FontStyle.Normal
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp,
                            fontStyle = FontStyle.Normal
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Won by: Team Name")
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { }) {
                    Text(text = stringResource(id = R.string.highlight))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPrevious() {
    HorizontalMatchCard(
        Match(
            away = "Testq",
            date = "2022-04-23T18:00:00.000Z",
            description = "Descriptiojn",
            highlights = "test highlights",
            home = "test2 ",
            winner = "Test1"
        )
    )
}