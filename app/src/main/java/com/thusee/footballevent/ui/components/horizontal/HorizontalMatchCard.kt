package com.thusee.footballevent.ui.components.horizontal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.ui.components.VideoPlayerScreen
import com.thusee.footballevent.ui.theme.WonByTextStyle
import com.thusee.footballevent.ui.utils.DateUtils
import com.thusee.footballevent.ui.utils.Utils.splitStringIfStartsWithTeam

@Composable
fun HorizontalMatchCard(
    match: Match,
    modifier: Modifier = Modifier,
    isFromDetails: Boolean = false
) {
    val (formattedTime, formattedDate) = remember {
        DateUtils.convertDateTime(match.date)
    }

    val configuration = LocalConfiguration.current
    val width = if (isFromDetails) {
        Modifier.width(configuration.screenWidthDp.times(.9).dp)
    } else {
        Modifier.fillMaxWidth()
    }

    var openVideoPlayer by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = modifier
            .then(width)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Column {
                    Text(
                        text = splitStringIfStartsWithTeam(match.home).remainingString,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight(600)
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.vs),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight(700)
                        ),
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = splitStringIfStartsWithTeam(match.away).remainingString,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight(600)
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Divider(
                    modifier = Modifier
                        .height(50.dp)
                        .width(1.dp)
                )

                Spacer(modifier = Modifier.width(32.dp))

                Column {
                    Text(
                        text = formattedDate,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontStyle = FontStyle.Normal
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontStyle = FontStyle.Normal
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (match.winner.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    WonBySpannedText(splitStringIfStartsWithTeam(match.winner).remainingString)

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(onClick = { openVideoPlayer = true }) {
                        Text(text = stringResource(id = R.string.highlight))
                    }
                }
            }
        }
    }
    if (openVideoPlayer) VideoPlayerScreen(
        videoUri = match.highlights,
        videoDescription = match.description
    ) {
        openVideoPlayer = false
    }
}

@Composable
fun WonBySpannedText(teamName: String) {
    Column {
        Text(
            text = stringResource(id = R.string.won_by),
            style = MaterialTheme.typography.WonByTextStyle.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.onBackground
            ),
        )

        Text(
            text = teamName,
            style = MaterialTheme.typography.WonByTextStyle
        )
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

@Preview
@Composable
fun PreviewWonBySpannedText() {
    WonBySpannedText(teamName = "Team Name")
}