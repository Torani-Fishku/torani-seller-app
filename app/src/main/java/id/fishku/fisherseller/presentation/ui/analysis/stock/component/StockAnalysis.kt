package id.fishku.fisherseller.presentation.ui.analysis.stock.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fisherseller.presentation.ui.dashboardv2.DashboardV2ViewModel
import id.fishku.fisherseller.presentation.ui.dashboardv2.StockSortType
import id.fishku.fisherseller.presentation.ui.dashboardv2.components.TitleAndDivider
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.GenericResponse
import kotlin.math.ceil

/**
 * StockAnalysis Composable Component
 *
 * Component to show stock analytics of fish sold by user
 */
@Composable
fun StockAnalysis(fetchFishState: Resource<GenericResponse<MenuModel>>?) {
    val viewModel = hiltViewModel<DashboardV2ViewModel>()
    val sortedState by viewModel.sortedStock

    TitleAndDivider("Stok Produk")
    Spacer(modifier = Modifier.height(24.dp))

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
            fishProducts = if (sortedState == StockSortType.ASC){
                fishProducts.sortedBy { it.weight }
            }else{
                fishProducts.sortedByDescending { it.weight }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height((ceil(fishProducts.size / 3.0) * 70).dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(fishProducts.size) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = fishProducts[it].name,
                            style = TextStyle(
                                fontFamily = fonts,
                                fontSize = 12.sp,
                                color = colorResource(R.color.grey_500)
                            )
                        )
                        Text(
                            text = fishProducts[it].weight.toString(),
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
        else -> Box {}
    }
}