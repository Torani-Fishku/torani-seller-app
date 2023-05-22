package id.fishku.fisherseller.presentation.ui.analysis.selling.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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


@Composable
fun SellingOverviewCard() {
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
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .padding(vertical = 16.dp, horizontal = 24.dp),
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
                        append("Anda telah menjual ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.blue),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    ) {
                        append("80 ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.blue),
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("produk ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.black),
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("dengan total pendapatan ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.blue),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    ) {
                        append("Rp12.100.000 ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.black),
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("sejak ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.blue),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    ) {
                        append("12/12/2022")
                    }

                },
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = fonts,
                    fontSize = 12.sp,
                )
            )
        }
    }
}