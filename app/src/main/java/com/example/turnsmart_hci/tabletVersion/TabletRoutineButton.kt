package com.example.turnsmart_hci.tabletVersion

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.ThemeColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TabletRoutineButton(
    onFavoriteClick: () -> Unit,
    label: String,
    enabled: Boolean = true,
    onPlayClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = ThemeColors.DARK_TEXT.color,
    isOn: Boolean = false,
    onButton: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(isOn) }
    val scope = rememberCoroutineScope()

    Button(
        onClick = { /* Handle button click if needed */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
            .padding(8.dp)
            .size(250.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    onClick = { onFavoriteClick() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.favorite),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        isPlaying = !isPlaying
                        onPlayClick()
                        if (isPlaying) {
                            scope.launch {
                                delay(5000)
                                isPlaying = false
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top= 16.dp)
                        .border(2.dp, Color.Black, CircleShape)
                        .background(Color.White, CircleShape),
                ) {
                    Icon(
                        painter = painterResource(if (isPlaying) R.drawable.pause else R.drawable.play_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = label,
                        color = textColor,
                        fontSize = 25.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TabletRoutineButtonPreview() {
    TabletRoutineButton(
        label = "Routine1",
        onPlayClick = {},
        onFavoriteClick = {},
        backgroundColor = ThemeColors.PALE_BLUE.color,
    )
}
