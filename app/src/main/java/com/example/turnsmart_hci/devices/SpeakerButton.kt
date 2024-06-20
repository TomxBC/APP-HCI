package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue
import com.example.turnsmart_hci.ui.theme.pale_green
import com.example.turnsmart_hci.ui.theme.pale_red

@Composable
fun SpeakerButton() {
    DeviceButton(
        label = R.string.speakers,
        onClick = {},
        backgroundColor = pale_blue,
        icon = R.drawable.speaker
    )
}

@Composable
fun SpeakerScreen(
    deviceName: String,
    volume: Int,
    onVolumeChange: (Int) -> Unit,
    onPlay: () -> Unit,
    onStop: () -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    genre: String,
    onGenreSelect: (String) -> Unit,
    textColor: Color = Color.Black
) {
    val genres = listOf("Pop", "Rock", "Jazz", "Classical", "Hip Hop")
    var expanded by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) } // Track if music is playing
    var showDialog by remember { mutableStateOf(false) } // Track if dialog is showing
    var currentSong by remember { mutableStateOf("Song 1") } // Track the current song

    val songs = when (genre) {
        "Pop" -> listOf("Song 1", "Song 2", "Song 3")
        "Rock" -> listOf("Song 4", "Song 5", "Song 6")
        "Jazz" -> listOf("Song 7", "Song 8", "Song 9")
        "Classical" -> listOf("Song 10", "Song 11", "Song 12")
        "Hip Hop" -> listOf("Song 13", "Song 14", "Song 15")
        else -> listOf()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.speaker),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = deviceName,
                color = textColor,
                fontSize = 22.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Now Playing Text
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(25.dp)
                ){
                    Text(
                        text = "Now Playing: $currentSong",
                        color = textColor,
                        fontSize = 16.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(25.dp).padding(10.dp))

                    Slider(
                        value = 0f,
                        onValueChange = {  },
                        valueRange = 0f..10f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Music Control Icons
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    ) {
                        IconButton(onClick = onPrevious) {
                            Icon(
                                painter = painterResource(R.drawable.skip_previous),
                                contentDescription = "Previous",
                                tint = textColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = onStop) {
                            Icon(
                                painter = painterResource(R.drawable.stop),
                                contentDescription = "Stop",
                                tint = textColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = {
                            if (isPlaying) {
                                onPause()
                            } else {
                                onPlay()
                            }
                            isPlaying = !isPlaying
                        }) {
                            Icon(
                                painter = if (isPlaying) painterResource(R.drawable.pause_circle) else painterResource(R.drawable.play_circle),
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = textColor,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        IconButton(onClick = onNext) {
                            Icon(
                                painter = painterResource(R.drawable.skip_next),
                                contentDescription = "Next",
                                tint = textColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { showDialog = true }) {
                            Icon(
                                painter = painterResource(R.drawable.queue_music),
                                contentDescription = "Queue",
                                tint = textColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    // Genre Selection
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Genre:",
                            color = textColor,
                            fontSize = 16.sp,
                            fontFamily = montserratFontFamily,
                            fontWeight = FontWeight.Medium,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Box {
                            Text(
                                text = genre,
                                color = textColor,
                                fontSize = 16.sp,
                                fontFamily = montserratFontFamily,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable { expanded = !expanded }
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                genres.forEach { selectedGenre ->
                                    DropdownMenuItem(
                                        text = { Text(selectedGenre) },
                                        onClick = {
                                            onGenreSelect(selectedGenre)
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text(text = "Playlist: $genre") },
                            text = {
                                Column {
                                    songs.forEach { song ->
                                        Text(text = song)
                                    }
                                }
                            },
                            confirmButton = {
                                Button(onClick = { showDialog = false }) {
                                    Text("Close")
                                }
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            // Volume Slider
            Text(
                text = "Volume:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$volume",
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { if (volume > 0) onVolumeChange(volume - 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Slider(
                    value = volume.toFloat(),
                    onValueChange = { newValue ->
                        onVolumeChange(newValue.toInt())
                    },
                    valueRange = 0f..10f,
                    steps = 0, // Ensures the slider snaps to integer values
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { if (volume < 100) onVolumeChange(volume + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SpeakerControlScreen() {
    var volume by remember { mutableStateOf(5) }
    var genre by remember { mutableStateOf("Pop") }

    SpeakerScreen(
        deviceName = "Living Room Speaker",
        volume = volume,
        onVolumeChange = { volume = it },
        onPlay = { /* TODO: Play music */ },
        onStop = { /* TODO: Stop music */ },
        onPause = { /* TODO: Pause music */ },
        onResume = { /* TODO: Resume music */ },
        onNext = { /* TODO: Next song */ },
        onPrevious = { /* TODO: Previous song */ },
        genre = genre,
        onGenreSelect = { genre = it }
    )
}

@Preview
@Composable
fun SpeakerButtonPreview() {
    SpeakerButton()
    SpeakerControlScreen()
}
