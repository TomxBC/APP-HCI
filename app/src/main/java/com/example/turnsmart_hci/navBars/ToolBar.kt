package com.example.turnsmart.navBars

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.lightToolbar
import com.example.turnsmart_hci.ui.theme.lightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnSmartToolbar(modifier: Modifier = Modifier) {
    TopAppBar (
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = lightToolbar,
            titleContentColor = lightText,
        ),
        modifier = modifier,
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                tint = lightText
            )
        }, actions = {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = stringResource(id = R.string.settings_label),
                tint = lightText
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TurnSmartTopAppBarPreview() {
    TurnSmartTheme {
        TurnSmartToolbar()
    }
}