package id.fishku.fisherseller.presentation.ui.analysis.weather.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TipsAndUpdates
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts

@Composable
fun InfoCard(info: String) {
    val iconSize = 50.dp
    val offsetHorizontalInPx = LocalDensity.current.run { (iconSize / 3).roundToPx() }
    val offsetVerticallInPx = LocalDensity.current.run { (iconSize * 5 / 8).roundToPx() }

    Box(modifier = Modifier.padding(top = 4.dp, end = 8.dp, start = 8.dp)) {
        Card(
            modifier = Modifier
                .height(120.dp)
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
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = info,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
        Icon(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = +offsetHorizontalInPx,
                        y = -offsetVerticallInPx
                    )
                }
                .size(iconSize)
                .align(Alignment.CenterEnd),
            imageVector = Icons.Rounded.TipsAndUpdates,
            contentDescription = "",
            tint = colorResource(R.color.blue),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoCardPreview() {
    Mdc3Theme {
        InfoCard(
            "Waspada Gelombang Laut kategori Sedang dengan ketinggian 1.25 - 2.5 meter " +
                    "berpeluang terjadi di Selat Karimata dan Laut Jawa bag. Barat."
        )
    }
}