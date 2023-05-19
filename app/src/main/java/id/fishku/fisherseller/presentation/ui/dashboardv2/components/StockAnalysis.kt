package id.fishku.fisherseller.presentation.ui.dashboardv2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts

/**
 * StockAnalysis Composable Component
 *
 * Component to show stock analytics of fish sold by user
 */
@Composable
fun StockAnalysis() {
    TitleAndDivider("Analisis Stok")
    val fish = listOf("Ikan tongkol", "Ikan Tuna", "Ikan Bandeng", "Ikan Mas", "Ikan Gabus")
    Spacer(modifier = Modifier.height(24.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = Modifier.height((120 * (fish.size / 3)).dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(fish.size) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = fish[it],
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 12.sp,
                        color = colorResource(R.color.grey_500)
                    )
                )
                Text(
                    text = "30",
                    style = TextStyle(
                        fontFamily = fonts,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}