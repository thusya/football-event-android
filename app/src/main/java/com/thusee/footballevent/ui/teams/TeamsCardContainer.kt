package com.thusee.footballevent.ui.teams

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.ui.utils.Utils.splitStringIfStartsWithTeam

@Composable
fun TeamCardContainer(
    team: Team,
    onTeamClick: (Team) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .clickable { onTeamClick(team) },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(team.logo),
                contentDescription = stringResource(id = R.string.cont_desc_team_logo),
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                val data = splitStringIfStartsWithTeam(team.name)
                Text(
                    text = data.teamPrefix,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = data.remainingString,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun TeamCardContainerPreview() {
    TeamCardContainer(
        team = Team(
            id = "1",
            name = "Team Red Dragons",
            logo = "https://tstzj.s3.amazonaws.com/dragons.png"
        ),
        onTeamClick = {}
    )
}