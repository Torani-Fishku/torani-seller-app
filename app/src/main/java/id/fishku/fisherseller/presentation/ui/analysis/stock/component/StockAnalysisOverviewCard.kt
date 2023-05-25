package id.fishku.fisherseller.presentation.ui.analysis.stock.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.GenericResponse

@Composable
fun StockAnalysisOverviewCard(fetchFishState: Resource<GenericResponse<MenuModel>>?) {
    when (fetchFishState?.status) {
        Status.LOADING -> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
        Status.ERROR -> {}
        Status.SUCCESS -> {
            var fishProducts: List<MenuModel> = fetchFishState.data?.data ?: emptyList()
            var totalWeight = fishProducts.sumOf { it.weight }

            Card(
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white),
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding( horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.black),
                                    fontFamily = fonts,
                                    fontWeight = FontWeight.Medium
                                )
                            ) {
                                append("Anda memiliki ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.blue),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                            ) {
                                append("${fishProducts.size} ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.blue),
                                    fontWeight = FontWeight.Bold,
                                )
                            ) {
                                append("produk ")
                            }
                        },
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 16.sp,
                        )
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.black),
                                    fontWeight = FontWeight.Medium
                                )
                            ) {
                                append("dengan total berat ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.blue),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                            ) {
                                append("$totalWeight kg")
                            }
                        },
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
        }
        else -> {}
    }
}

