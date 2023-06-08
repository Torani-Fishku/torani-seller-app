package id.fishku.fisherseller.presentation.ui.analysis.weather.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.entryModelOf
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberChartStyle
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberLegend
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberMarker
import id.fishku.fishersellercore.response.WeatherAndTideItem
import id.fishku.fishersellercore.response.WeatherAndTideResponse

@Composable
fun WindChartWithTitle(weatherAndTideData: WeatherAndTideResponse) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Kecepatan Angin",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue),
            )
        )
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
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            "${weatherAndTideData.data?.get(0)?.windSpeedMin} - ${
                weatherAndTideData.data?.get(
                    0
                )?.windSpeedMax
            } km/h",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.black),
            )
        )
        Icon(
            imageVector = Icons.Outlined.ArrowDownward,
            contentDescription = "Arrow",
            tint = colorResource(R.color.red_error),
            modifier = Modifier.size(12.dp)
        )
        Text(
            "1%  ",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.red_error),
            )
        )
        Text(
            "vs Kemarin",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.grey_500),
            )
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    InfoCard(weatherAndTideData.data?.get(0)?.weatherDesc ?: "-")
    Spacer(modifier = Modifier.height(8.dp))
    WindChart(weatherAndTideData.data ?: emptyList())
}

@Composable
fun WindChart(items: List<WeatherAndTideItem?>) {
    val listOfXAxis: ArrayList<String> = arrayListOf()
    val listOfData: ArrayList<Float> = arrayListOf()
    for (item in items) {
        if (item != null) {
            val windSpeedMin: Float = item.windSpeedMin?.toFloat() ?: 0f
            val windSpeedMax: Float = item.windSpeedMax?.toFloat() ?: 0f
            listOfData.add((windSpeedMin + windSpeedMax) / 2)
            listOfXAxis.add(item.timeDesc ?: "-")
        }
    }
    val bottomAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _ -> listOfXAxis[x.toInt() - 1] }
    val chartEntryModel =
        entryModelOf(
            1 to listOfData[0],
            2 to listOfData[1],
            3 to listOfData[2],
            4 to listOfData[3],
        )
    val marker = rememberMarker()
    val chartColors = listOf(colorResource(R.color.blue))
    ProvideChartStyle(rememberChartStyle(chartColors)) {
        Chart(
            chart = lineChart(persistentMarkers = remember(marker) { mapOf(10f to marker) }),
            startAxis = startAxis(),
            bottomAxis = bottomAxis(valueFormatter = bottomAxisValueFormatter),
            marker = marker,
            legend = rememberLegend("Kecepatan Angin dalam km/h"),
            model = chartEntryModel
        )
    }
}