package id.fishku.fisherseller.presentation.ui.dashboardv2.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.analysis.weather.WeatherAnalysisActivity
import id.fishku.fishersellercore.response.WeatherAndTideResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAndTideCard(weatherAndTideData: WeatherAndTideResponse) {
    val context = LocalContext.current
    val iconSize = 120.dp
    val offsetHorizontalInPx = LocalDensity.current.run { (iconSize / 7).roundToPx() }
    val offsetVerticallInPx = LocalDensity.current.run { (iconSize * 3 / 8).roundToPx() }

    Box(modifier = Modifier.padding(top = 4.dp, end = 4.dp, start = 4.dp)) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        weatherAndTideData.issued?.replace("UTC", "") ?: "",
                        style = TextStyle(fontFamily = fonts, fontSize = 10.sp)
                    )
                    Text(
                        weatherAndTideData.name ?: "",
                        style = TextStyle(fontFamily = fonts, fontSize = 10.sp)
                    )
                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 16.dp)
                        ) {
                            Box {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "${weatherAndTideData.data?.get(0)?.waveDesc}",
                                        style = TextStyle(
                                            fontFamily = fonts,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        ),
                                    )
                                    Text(
                                        "Gelombang ${weatherAndTideData.data?.get(0)?.waveCat}",
                                        style = TextStyle(
                                            fontFamily = fonts,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                        ),
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box {
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,) {
                                    Text(
                                        "${weatherAndTideData.data?.get(0)?.windSpeedMin} - ${
                                            weatherAndTideData.data?.get(
                                                0
                                            )?.windSpeedMax
                                        } km/h",
                                        style = TextStyle(
                                            fontFamily = fonts,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        )
                                    )
                                    Text(
                                        "${weatherAndTideData.data?.get(0)?.weather}",
                                        style = TextStyle(
                                            fontFamily = fonts,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.img_wave),
                    contentDescription = "Cloud Image",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }

            // Second part of the card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White)
                    .padding(top = 8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "Aman untuk memancing ikan",
                            textAlign = TextAlign.Right,
                            color = colorResource(R.color.blue),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box {
                            Row {
                                Icon(
                                    modifier = Modifier
                                        .size(18.dp),
                                    painter = painterResource(id = R.drawable.ic_fish),
                                    contentDescription = "",
                                    tint = colorResource(R.color.blue),
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    modifier = Modifier
                                        .size(18.dp),
                                    painter = painterResource(id = R.drawable.ic_fish),
                                    contentDescription = "",
                                    tint = colorResource(R.color.blue),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    modifier = Modifier
                                        .size(18.dp),
                                    painter = painterResource(id = R.drawable.ic_fish),
                                    contentDescription = "",
                                    tint = colorResource(R.color.blue).copy(alpha = 0.5f),
                                )

                            }
                        }

                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        TextButton(
                            onClick = {
                                val intent = Intent(context, WeatherAnalysisActivity::class.java)
                                context.startActivity(intent)
                            }, modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Text(
                                text = "Lihat Detail",
                                textAlign = TextAlign.Right,
                                style = TextStyle(textDecoration = TextDecoration.Underline)
                            )
                        }
                    }

                }
            }
        }
        Icon(
            modifier = Modifier
                .offset {
                    IntOffset(x = +offsetHorizontalInPx, y = -offsetVerticallInPx)
                }
                .size(iconSize)
                .align(Alignment.TopEnd),
            imageVector = Icons.Filled.Cloud,
            contentDescription = "",
            tint = colorResource(R.color.blue_500),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardV2ScreenPreview() {
    Mdc3Theme {
        WeatherAndTideCard(WeatherAndTideResponse())
    }
}