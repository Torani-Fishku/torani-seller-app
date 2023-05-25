package id.fishku.fisherseller.presentation.ui.analysis.price.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.entry.entryModelOf
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberChartStyle
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberLegend
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberMarker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendPredictionPriceChart() {
    Text(
        "Tren dan Prediksi Harga Ikan",
        style = TextStyle(
            fontFamily = fonts,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.blue),
        ),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextButton(
            onClick = { },
        ) {
            Icon(
                Icons.Filled.ExpandMore,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Text("Bandeng", style = TextStyle(fontFamily = fonts, fontSize = 12.sp))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }


        TextButton(
            onClick = { },
        ) {
            Text("Minggu Ini", style = TextStyle(fontFamily = fonts, fontSize = 12.sp))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Icon(
                Icons.Filled.ExpandMore,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    WeatherChart()
}

@Composable
fun WeatherChart() {
    val chartEntryModel =
        entryModelOf(1 to 30000f, 2 to 40000f, 3 to 38000f, 4 to 40000, 5 to 41000, 6 to 42000, 7 to 35000)
    val marker = rememberMarker()
    val chartColors = listOf(colorResource(R.color.blue))
    ProvideChartStyle(rememberChartStyle(chartColors)) {
        Chart(
            chart = lineChart(persistentMarkers = remember(marker) { mapOf(10f to marker) }),
            startAxis = startAxis(),
            bottomAxis = bottomAxis(guideline = null),
            marker = marker,
            legend = rememberLegend("Harga Ikan"),
            model = chartEntryModel
        )
    }
}
