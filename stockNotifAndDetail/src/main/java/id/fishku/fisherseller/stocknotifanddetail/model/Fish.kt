package id.fishku.fisherseller.stocknotifanddetail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fish(
    val name : String,
    val stock : Int,
    val price : Int,
    val desc : String,
    val sellerName : String,
    val tpiLoc : String,
    val location: String,
    val photo : Int?= null
) : Parcelable
