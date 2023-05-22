package id.fishku.fisherseller.presentation.ui.dashboardv2

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fishku.fisherseller.seller.domain.repository.Repository
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.GenericResponse
import javax.inject.Inject

/**
 * DashboardV2 view model
 *
 * @property repo
 * @constructor Create empty Home view model
 */
@HiltViewModel
class DashboardV2ViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _sorted = mutableStateOf(StockSortType.ASC)
    val sorted: State<StockSortType> = _sorted

    /**
     * Get list fish
     *
     * @param idSeller
     * @return
     */
    fun getListFish(idSeller: String): LiveData<Resource<GenericResponse<MenuModel>>> {
        return repo.getAllFish(idSeller).asLiveData()
    }

    fun changeStockSort(stockSortType: StockSortType){
        _sorted.value = stockSortType
    }

}
