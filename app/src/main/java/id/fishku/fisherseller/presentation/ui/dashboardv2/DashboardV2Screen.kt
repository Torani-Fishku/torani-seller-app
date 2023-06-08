package id.fishku.fisherseller.presentation.ui.dashboardv2

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.presentation.ui.dashboardv2.components.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fisherseller.presentation.ui.analysis.price.PriceAnalysisActivity
import id.fishku.fisherseller.presentation.ui.analysis.selling.SellingAnalysisActivity
import id.fishku.fisherseller.presentation.ui.analysis.stock.StockAnalysisActivity
import id.fishku.fisherseller.presentation.ui.notification.StockNotifActivity
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.response.WeatherAndTideResponse

/**
 * DashboardV2 Composable Screen
 *
 * Main Screen of Dashboard Version 2
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardV2Screen(userName: String?, weatherAndTiderRes: Resource<WeatherAndTideResponse>?) {
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                ),
                title = {
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
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(24.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.blue),
                                fontFamily = fonts,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Hai, ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.blue),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )
                        ) {
                            append("$userName ")
                        }
                    },
                    textAlign = TextAlign.Left,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 16.sp,
                    )
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.blue),
                                fontFamily = fonts,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("Ayo cek info terbaru bersama ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.blue),
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("Fishku! ")
                        }
                    },
                    textAlign = TextAlign.Left,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                TitleAndDivider(title = "Analisis dan Prediksi")
                Spacer(modifier = Modifier.height(24.dp))
                when (weatherAndTiderRes?.status) {
                    Status.LOADING -> Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                    Status.ERROR -> Text("Error :(( ${weatherAndTiderRes.message}")
                    Status.SUCCESS -> {
                        weatherAndTiderRes.data?.let {
                            WeatherAndTideCard(weatherAndTideData = it)
                        }
                    }
                    else -> Box {}
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, StockAnalysisActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ic_stock),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            val intent = Intent(context, PriceAnalysisActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ic_harga_ikan),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            val intent = Intent(context, SellingAnalysisActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ic_penjualan),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
                ProductData()
            }
        })
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashboardV2ScreenPreview() {
    Mdc3Theme {
        DashboardV2Screen(null, null)
    }
}