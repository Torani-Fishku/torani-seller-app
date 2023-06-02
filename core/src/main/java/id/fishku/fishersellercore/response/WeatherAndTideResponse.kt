package id.fishku.fishersellercore.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class WeatherAndTideResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("data")
	val data: List<WeatherAndTideItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("issued")
	val issued: String? = null
) : Parcelable

@Parcelize
data class WeatherAndTideItem(

	@field:SerializedName("wind_speed_min")
	val windSpeedMin: Int? = null,

	@field:SerializedName("wind_from")
	val windFrom: String? = null,

	@field:SerializedName("valid_from")
	val validFrom: String? = null,

	@field:SerializedName("time_desc")
	val timeDesc: String? = null,

	@field:SerializedName("wave_cat")
	val waveCat: String? = null,

	@field:SerializedName("station_remark")
	val stationRemark: String? = null,

	@field:SerializedName("wave_desc")
	val waveDesc: String? = null,

	@field:SerializedName("wind_speed_max")
	val windSpeedMax: Int? = null,

	@field:SerializedName("valid_to")
	val validTo: String? = null,

	@field:SerializedName("weather")
	val weather: String? = null,

	@field:SerializedName("warning_desc")
	val warningDesc: String? = null,

	@field:SerializedName("weather_desc")
	val weatherDesc: String? = null,

	@field:SerializedName("wind_to")
	val windTo: String? = null
) : Parcelable
