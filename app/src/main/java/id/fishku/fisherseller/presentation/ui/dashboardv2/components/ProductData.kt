package id.fishku.fisherseller.presentation.ui.dashboardv2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.presentation.ui.dashboardv2.theme.fonts

/**
 * ProductData Composable Component
 *
 * Component to show least and best selling fish at Dashboard
 */
@Composable
fun ProductData() {
    TitleAndDivider("Data Product")
    ProductDataCard("Penjualan Ikan Paling Banyak")
    Divider(thickness = 1.dp)
    ProductDataCard("Penjualan Ikan Paling Sedikit")
}


/**
 * ProductDataCard Composable Component
 *
 * Card Component of each least and best selling fish info
 */
@Composable
fun ProductDataCard(title: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "My Image",
            modifier = Modifier.width(100.dp).height(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.height(100.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Column {
                    Text(title, fontSize = 12.sp)
                    Text(
                        "Ikan Tuna",
                        style = TextStyle(
                            fontFamily = fonts, fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                    )
                }
            }

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    "76", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fonts,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "terjual", style = TextStyle(
                        fontFamily = fonts, fontSize = 12.sp, platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }

    }

}