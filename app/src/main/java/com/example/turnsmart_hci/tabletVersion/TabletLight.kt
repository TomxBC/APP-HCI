package com.example.turnsmart_hci.tabletVersion

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.pale_yellow


@Composable
fun LightButtonTablet(name: String) {
    TabletDeviceButton(label = R.string.lights,
        onClick = { /*TODO*/ },
        backgroundColor = pale_yellow,
        icon = R.drawable.lights,
        status = "Off")
}

@Composable
fun TabletLightsControl(){

}

@Preview
@Composable
fun ButtonPreviewTablet() {
    LightButtonTablet("Lights")
}