package com.example.turnsmart_hci.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.tabletVersion.TabletRoutineButton
import com.example.turnsmart_hci.ui.theme.ThemeColors
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.data.ui.routines.RoutineViewModel
import com.example.turnsmart_hci.data.ui.routines.RoutinesViewModel
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.routines.RoutineButton
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun AutomationScreen(
    manyRoutinesViewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
    routineViewModel: RoutineViewModel = viewModel(factory = getViewModelFactory()),
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType
) {
    val uiManyRoutinesState by manyRoutinesViewModel.uiState.collectAsState()

    TurnSmartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val routines = uiManyRoutinesState.routines

                if (routines.isEmpty()) {
                    Text(
                        text = "You don't have routines",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontFamily = montserratFontFamily,
                        color = TurnSmartTheme.colors.onPrimary
                    )
                } else {
                    Text(
                        text = "Your Routines",
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(20.dp),
                        color = TurnSmartTheme.colors.onPrimary
                    )

                    if (layoutType == NavigationSuiteType.NavigationRail || layoutType == NavigationSuiteType.NavigationDrawer) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(routines.size) { index ->
                                TabletRoutineButton(
                                    routine = routines[index],
                                    routineViewModel = routineViewModel,
                                    notificationViewModel = notificationViewModel
                                )
                            }
                        }
                    } else {
                        routines.forEach { routine ->
                            RoutineButton(
                                routine = routine,
                                routineViewModel = routineViewModel,
                                notificationViewModel = notificationViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
