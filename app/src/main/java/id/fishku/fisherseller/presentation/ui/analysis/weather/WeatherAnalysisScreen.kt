package id.fishku.fisherseller.presentation.ui.analysis.weather

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.DashboardActivity
import id.fishku.fisherseller.presentation.ui.analysis.weather.component.BestCatchCard
import id.fishku.fisherseller.presentation.ui.analysis.weather.component.StatusOverviewCard
import id.fishku.fisherseller.presentation.ui.analysis.weather.component.TideChartWithTitle
import id.fishku.fisherseller.presentation.ui.analysis.weather.component.WeatherChartWithTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAnalysisScreen() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)){
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = colorResource(R.color.white)
            ),
            navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(context, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    context.startActivity(intent)
                }) {
                    Icon(
                        Icons.Rounded.ChevronLeft,
                        contentDescription = "Back",
                        tint = colorResource(R.color.blue),
                    )
                }
            },
            title = {
                Text(
                    "ANALISIS CUACA DAN GELOMBANG LAUT",
                    style = TextStyle(
                        fontFamily = fonts,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.blue),
                        fontSize = 20.sp,
                    ),
                    textAlign = TextAlign.Center
                )
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            StatusOverviewCard()
            Spacer(modifier = Modifier.height(8.dp))
            BestCatchCard()
            TideChartWithTitle()
            WeatherChartWithTitle()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun WeatherAnalysisScreenPreview() {
    Mdc3Theme {
        WeatherAnalysisScreen()
    }
}