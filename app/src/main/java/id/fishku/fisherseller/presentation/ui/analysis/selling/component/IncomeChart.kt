package id.fishku.fisherseller.presentation.ui.analysis.selling.component

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
import com.patrykandpatrick.vico.core.entry.entryModelOf
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberChartStyle
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberLegend
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberMarker

@Composable
fun IncomeChartWithTitle() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Total Pendapatan",
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
            "Rp100000 ",
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
            "2.1%  ",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.red_error),
            )
        )
        Text(
            "vs Minggu Lalu",
            style = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.grey_500),
            )
        )
    }
    Spacer(modifier = Modifier.height(8.dp))

    IncomeChart()
}

@Composable
fun IncomeChart() {
    val chartEntryModel = entryModelOf(1 to 4f, 2 to 12f, 3 to 8f, 4 to 16f, 5 to 6f, 6 to 6f, 7 to 8f)
    val marker = rememberMarker()
    val chartColors = listOf(colorResource(R.color.blue))
    ProvideChartStyle(rememberChartStyle(chartColors)) {
        Chart(
            chart = lineChart(persistentMarkers = remember(marker) { mapOf(10f to marker) }),
            startAxis = startAxis(),
            bottomAxis = bottomAxis(guideline = null),
            marker = marker,
            legend = rememberLegend("Pendapatan Dalam Ribu Rupiah"),
            model = chartEntryModel
        )
    }
}