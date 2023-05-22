package id.fishku.fisherseller.presentation.ui.dashboardv2.components

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.presentation.ui.analysis.selling.SellingAnalysisActivity

/**
 * SalesAnalysis Composable Component
 *
 * Component to show sales analytics of user
 */
@Composable
fun SalesAnalysis() {
    val context = LocalContext.current

    TitleAndDivider("Analisis Penjualan", onClick = {
        val intent = Intent(context, SellingAnalysisActivity::class.java)
        context.startActivity(intent)
    })

    Spacer(modifier = Modifier.height(24.dp))
    Text(
        "Jumlah dikunjungi",
        style = TextStyle(
            fontFamily = fonts,
            fontSize = 12.sp,
            color = colorResource(R.color.grey_500)
        )
    )
    Text(
        "45",
        style = TextStyle(
            fontFamily = fonts,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    )

    Spacer(modifier = Modifier.height(24.dp))
    Text(
        "Total produk terjual", style = TextStyle(
            fontFamily = fonts,
            fontSize = 12.sp,
            color = colorResource(R.color.grey_500)
        )
    )
    Text(
        "95", style = TextStyle(
            fontFamily = fonts,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    )

    Spacer(modifier = Modifier.height(24.dp))
    Text(
        "Total Pendapatan", style = TextStyle(
            fontFamily = fonts,
            fontSize = 12.sp,
            color = colorResource(R.color.grey_500)
        )
    )
    Text(
        "Rp100000",
        style = TextStyle(
            fontFamily = fonts,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}