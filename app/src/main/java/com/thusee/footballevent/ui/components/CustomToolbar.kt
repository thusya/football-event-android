package com.thusee.footballevent.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    modifier: Modifier,
    title: String,
    showBackButton: Boolean = false,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.cont_desc_back_arrow)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CustomToolbarWithoutBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Red Dragons",
    )
}

@Preview
@Composable
fun CustomToolbarWithBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Red Dragons",
        showBackButton = true
    )
}
