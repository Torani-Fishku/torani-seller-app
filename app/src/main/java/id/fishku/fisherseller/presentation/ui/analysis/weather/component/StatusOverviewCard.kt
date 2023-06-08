package id.fishku.fisherseller.presentation.ui.analysis.weather.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts

@Composable
fun StatusOverviewCard() {
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Status Saat Ini:",
                style = TextStyle(
                    fontFamily = fonts,
                    fontSize = 12.sp,
                    color = colorResource(R.color.blue),
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                "Aman Untuk Memancing Ikan",
                style = TextStyle(
                    fontFamily = fonts,
                    fontSize = 12.sp,
                    color = colorResource(R.color.black),
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Prediksi Rate Tangkapan:",
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 12.sp,
                        color = colorResource(R.color.blue),
                        fontWeight = FontWeight.Medium
                    )
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
        }
    }
}