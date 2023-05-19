package id.fishku.fisherseller.presentation.ui.dashboardv2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.presentation.ui.dashboardv2.components.*
import androidx.compose.ui.text.TextStyle
import id.fishku.fisherseller.compose.theme.fonts

/**
 * DashboardV2 Composable Screen
 *
 * Main Screen of Dashboard Version 2
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardV2Screen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                "DASHBOARD",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = fonts,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                )
            )
        }, actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        })
    }, content = { innerPadding ->
        Column(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(innerPadding)
                    .padding(24.dp)
            ) {
                WeatherAndTideCard()
                Spacer(modifier = Modifier.height(24.dp))
                ProductData()
                Spacer(modifier = Modifier.height(16.dp))
                SalesAnalysis()
                Spacer(modifier = Modifier.height(16.dp))
                StockAnalysis()
            }
        }
    })
}


@Preview(showBackground = true)
@Composable
fun NewDashboardScreenPreview() {
    Mdc3Theme {
        DashboardV2Screen()
    }
}