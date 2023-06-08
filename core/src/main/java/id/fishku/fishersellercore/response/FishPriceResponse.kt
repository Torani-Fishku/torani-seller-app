package id.fishku.fishersellercore.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FishPriceResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("predicted_price")
	val predictedPrice: Float? = null
) : Parcelable
