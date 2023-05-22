package id.fishku.fisherseller.compose.utils

sealed class UiState<out T: Any?> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T: Any>(val message: String) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}