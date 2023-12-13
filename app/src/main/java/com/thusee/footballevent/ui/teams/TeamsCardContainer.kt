package com.thusee.footballevent.ui.teams

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Team

@Composable
fun TeamCardContainer(
    team: Team,
    onTeamClick: (Team) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable { onTeamClick(team) }
            .semantics {
                contentDescription = ""
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = rememberAsyncImagePainter(team.logo),
                contentDescription = stringResource(id = R.string.cont_desc_team_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = team.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
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