package com.example.turnsmart_hci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurnSmartTheme {
                Surface ( modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val viewModel by viewModels<NotificationViewModel>()
                    MainScreen(viewModel)
                }
            }
        }
    }
}