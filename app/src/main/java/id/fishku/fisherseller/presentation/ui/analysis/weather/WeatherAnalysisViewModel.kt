package id.fishku.fisherseller.presentation.ui.analysis.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byn.analytics.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.WeatherAndTideRequest
import id.fishku.fishersellercore.response.WeatherAndTideResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherAnalysisViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private var _weatherRes = MutableLiveData<Resource<WeatherAndTideResponse>>()
    val weatherResponse: LiveData<Resource<WeatherAndTideResponse>> get() = _weatherRes

    fun getWeatherAndTideInfo(request: WeatherAndTideRequest){
        viewModelScope.launch {
            repository.getWeatherAndTideInfo(request).collect{
                _weatherRes.value = it
            }

        }
    }
}