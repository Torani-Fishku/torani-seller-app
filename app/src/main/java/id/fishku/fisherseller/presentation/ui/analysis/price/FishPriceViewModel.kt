package id.fishku.fisherseller.presentation.ui.analysis.price

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byn.analytics.domain.FishPriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.FishPriceRequest
import id.fishku.fishersellercore.response.FishPriceResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FishPriceViewModel @Inject constructor(
    private val repository: FishPriceRepository
) : ViewModel() {

    val fishTypes: ArrayList<String> =
        arrayListOf("bandeng", "kembung_lelaki", "tenggiri", "tongkol_abu", "tongkol_komo")

    val fishTypesDropdown: ArrayList<String> =
        arrayListOf("Bandeng", "Kembung Lelaki", "Tenggiri", "Tongkol Abu", "Tongkol Komo")

    private var _fishPriceRequest = mutableStateOf(FishPriceRequest())
    val fishPriceRequest: State<FishPriceRequest> = _fishPriceRequest

    private val _listFishPrice = MutableLiveData<ArrayList<Resource<FishPriceResponse>>>()
    val listFishPrice: LiveData<ArrayList<Resource<FishPriceResponse>>> get() = _listFishPrice

    fun getAllFishPrice() {
        viewModelScope.launch {
            _listFishPrice.value = arrayListOf()
            if(_fishPriceRequest.value.fishType == fishTypes[1]){
                (9..15).map { date ->
                    getFishPrice(date)
                }
            }else{
                (8..14).map { date ->
                    getFishPrice(date)
                }
            }

        }
    }

    suspend fun getFishPrice(date: Int) {
        _fishPriceRequest.value = FishPriceRequest(
            fishType = _fishPriceRequest.value.fishType,
            date = "2023-02-$date"
        )
        repository.getFishPrice(_fishPriceRequest.value).collect{
            resource ->
            val updatedList = _listFishPrice.value ?: arrayListOf()
            if(resource.status == Status.SUCCESS){
                updatedList[updatedList.size - 1] = resource
            }else{
                updatedList.add(resource)
            }
            _listFishPrice.postValue(updatedList)
        }
    }

    fun changeFishType(fishType: String) {
        _fishPriceRequest.value =
            FishPriceRequest(
                fishType = fishTypes[fishTypesDropdown.indexOf(fishType)],
                date = _fishPriceRequest.value.date
            )
        getAllFishPrice()
    }

}