package com.example.turnsmart_hci.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.data.ui.routines.RoutinesViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

@Composable
fun AutomationScreen(
    manyRoutinesViewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
    routineViewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiManyRoutinesState by manyRoutinesViewModel.uiState.collectAsState()
    val uiRoutineState by routineViewModel.uiState.collectAsState()

    TurnSmartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var routines = uiManyRoutinesState.routines
                if(routines.isEmpty()) {
                    Text(
                        text = "You don't have routines",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                } else {
                    routines.forEach { routine -> }
                    //Text(text = "Automation Screen", fontFamily = montserratFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, color = dark_purple)
                }
            }
        }
    }

}