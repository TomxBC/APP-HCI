package com.example.turnsmart.navBars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

@Composable
fun TurnSmartBottomNavigationBar(modifier: Modifier = Modifier) {
    NavigationBar (
        containerColor = colorScheme.primary,
        contentColor = colorScheme.onPrimary,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = stringResource(id = R.string.home_label)
                )
            },
            label = {
                Text(stringResource(R.string.home_label))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.routines),
                    contentDescription = stringResource(id = R.string.automation_label)
                )
            },
            label = {
                Text(stringResource(R.string.automation_label))
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.devices),
                    contentDescription = stringResource(id = R.string.devices_label)
                )
            },
            label = {
                Text(stringResource(R.string.devices_label))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val largePadding = dimensionResource(R.dimen.large_padding)

    TurnSmartTheme {
        TurnSmartBottomNavigationBar(Modifier.padding(top = largePadding))
    }
}

