package id.fishku.fishersellercore.requests

data class FishPriceRequest(
    val fishType: String? = "bandeng",
    val date: String? = "2023-02-08",
)
