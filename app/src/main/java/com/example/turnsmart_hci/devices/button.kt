package com.example.turnsmart_hci.devices

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.darkText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun CustomButton(
    label: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = darkText,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
            .padding(8.dp)
            .size(width = 300.dp, height = 50.dp),
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.padding(start = 0.dp).size(24.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            color = textColor,
            fontSize = 18.sp,
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(100.dp))
        IconButton(
            onClick = { }
        ){
            Icon(
                painter = painterResource(R.drawable.favorite),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black)
        }

    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(label = stringResource(id = R.string.lights), onClick = {},icon = R.drawable.lights, backgroundColor = pale_blue)
}