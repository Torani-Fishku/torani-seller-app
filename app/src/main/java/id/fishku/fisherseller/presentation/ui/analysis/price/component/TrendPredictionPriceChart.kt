package id.fishku.fisherseller.presentation.ui.analysis.price.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.entry.entryModelOf
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fisherseller.presentation.ui.analysis.price.FishPriceViewModel
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberChartStyle
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberLegend
import id.fishku.fisherseller.presentation.ui.analysis.style.rememberMarker
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.FishPriceRequest
import id.fishku.fishersellercore.response.FishPriceResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendPredictionPriceChart(fishPricePredict: ArrayList<Resource<FishPriceResponse>>?) {
    if (fishPricePredict != null && fishPricePredict.isNotEmpty()) {
        var expanded by remember { mutableStateOf(false) }
        val viewModel = hiltViewModel<FishPriceViewModel>()
        val fishPriceReq by viewModel.fishPriceRequest

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
            Box {
                TextButton(
                    onClick = { expanded = !expanded },
                ) {
                    Text(
                        viewModel.fishTypesDropdown[viewModel.fishTypes.indexOf(fishPriceReq.fishType)],
                        style = TextStyle(fontFamily = fonts, fontSize = 12.sp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Icon(
                        Icons.Filled.ExpandMore,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    for (fishType in viewModel.fishTypesDropdown) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    fishType,
                                    style = TextStyle(
                                        fontFamily = fonts,
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = false
                                        )
                                    )
                                )
                            },
                            onClick = {
                                viewModel.changeFishType(fishType)
                                expanded = false
                            }
                        )
                    }
                }
            }

            TextButton(
                onClick = { },
            ) {
                Text("Februari", style = TextStyle(fontFamily = fonts, fontSize = 12.sp))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.ExpandMore,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        when {
            fishPricePredict.all { it.status == Status.SUCCESS } -> WeatherChart(
                fishPricePredict,
                fishPriceReq
            )
            else -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun WeatherChart(
    fishPricePredict: ArrayList<Resource<FishPriceResponse>>,
    fishPriceReq: FishPriceRequest
) {
    val listOfData: ArrayList<Float> = arrayListOf()
    for (item in fishPricePredict) {
        item.data?.predictedPrice?.let { listOfData.add(it) }
    }

    if (listOfData.size == 7) {
        val chartEntryModel = if (fishPriceReq.fishType == "kembung_lelaki")
            entryModelOf(
                9 to listOfData[0],
                10 to listOfData[1],
                11 to listOfData[2],
                12 to listOfData[3],
                13 to listOfData[4],
                14 to listOfData[5],
                15 to listOfData[6]
            ) else entryModelOf(
            8 to listOfData[0],
            9 to listOfData[1],
            10 to listOfData[2],
            11 to listOfData[3],
            12 to listOfData[4],
            13 to listOfData[5],
            14 to listOfData[6]
        )
        val marker = rememberMarker()
        val chartColors = listOf(colorResource(R.color.blue))
        ProvideChartStyle(rememberChartStyle(chartColors)) {
            Chart(
                chart = lineChart(),
                startAxis = startAxis(),
                bottomAxis = bottomAxis(guideline = null),
                marker = marker,
                legend = rememberLegend("Harga Ikan"),
                model = chartEntryModel
            )
        }
    }
}
