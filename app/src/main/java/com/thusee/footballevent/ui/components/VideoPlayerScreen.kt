package com.thusee.footballevent.ui.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayerScreen(
    videoUri: String,
    videoDescription: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp,
            border = BorderStroke(5.dp, Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    val context = LocalContext.current
                    val exoPlayer = remember {
                        ExoPlayer.Builder(context).build().apply {
                            val mediaItem = MediaItem.fromUri(Uri.parse(videoUri))
                            setMediaItem(mediaItem, true)
                            prepare()
                            playWhenReady = true
                        }
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            exoPlayer.release()
                        }
                    }

                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = {
                            PlayerView(it).apply {
                                player = exoPlayer
                                useController = true
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = "Highlights of $videoDescription",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}