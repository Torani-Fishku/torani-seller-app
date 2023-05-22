package id.fishku.fisherseller.presentation.ui.dashboardv2

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.presentation.ui.dashboardv2.components.*
import androidx.compose.ui.text.TextStyle
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.notification.StockNotifActivity
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.GenericResponse

/**
 * DashboardV2 Composable Screen
 *
 * Main Screen of Dashboard Version 2
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardV2Screen(fetchFishState: Resource<GenericResponse<MenuModel>>?) {
    val context = LocalContext.current

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
            IconButton(onClick = {
                val intent = Intent(context, StockNotifActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        })
    }, content = { innerPadding ->
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
            StockAnalysis(fetchFishState)
        }
    })
}


@Preview(showBackground = true)
@Composable
fun DashboardV2ScreenPreview() {
    Mdc3Theme {
        DashboardV2Screen(null)
    }
}