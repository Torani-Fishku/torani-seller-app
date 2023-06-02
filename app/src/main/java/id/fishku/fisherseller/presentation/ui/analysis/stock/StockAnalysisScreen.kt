package id.fishku.fisherseller.presentation.ui.analysis.stock

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material3.*
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
import id.fishku.fisherseller.presentation.ui.DashboardActivity
import id.fishku.fisherseller.presentation.ui.analysis.stock.component.StockAnalysis
import id.fishku.fisherseller.presentation.ui.analysis.stock.component.StockAnalysisOverviewCard
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.GenericResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockAnalysisScreen (fetchFishState : Resource<GenericResponse<MenuModel>>?){
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = colorResource(R.color.white)
            ),
            navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(context, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    context.startActivity(intent)

                }) {
                    Icon(
                        Icons.Rounded.ChevronLeft,
                        contentDescription = "Back",
                        tint = colorResource(R.color.blue),
                        modifier = Modifier.size(35.dp)
                    )
                }
            },
            title = {
                Text(
                    "ANALISIS STOK",
                    style = TextStyle(
                        fontFamily = fonts,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.blue),
                        fontSize = 20.sp,
                    )
                )
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        StockAnalysisOverviewCard(fetchFishState)
        Spacer(modifier = Modifier.height(32.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            StockAnalysis(fetchFishState = fetchFishState)
        }
    }
}