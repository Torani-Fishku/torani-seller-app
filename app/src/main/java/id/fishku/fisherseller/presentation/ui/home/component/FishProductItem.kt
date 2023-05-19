package id.fishku.fisherseller.presentation.ui.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.util.convertCurrencyFormat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishProductItem(
    fishProduct: MenuModel,
    funcEdit: ((MenuModel) -> Unit)?,
    funcDelete: ((MenuModel) -> Unit)?
) {
    val sheetScope = rememberCoroutineScope()
    val moreSheetState = rememberModalBottomSheetState()
    val changePriceSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val changeStockSheetState = rememberModalBottomSheetState()

    var photoUrl =
        "https://cdn.discordapp.com/attachments/888781658566848532/1108666128936484905/img_product_placeholder.png"
    if (fishProduct.photo_url.isNotEmpty()) {
        photoUrl = id.fishku.fishersellercore.util.Constants.URL_IMAGE + fishProduct.photo_url
    }


    Card(
        modifier = Modifier.height(180.dp).fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column {
            Row(modifier = Modifier.weight(1.5f).padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                AsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data(photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Translated description of what the image contains",
                    placeholder = painterResource(R.drawable.img_product_placeholder),
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        fishProduct.name,
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 18.sp,
                            color = colorResource(R.color.black),
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    Text(
                        stringResource(
                            R.string.price_convert,
                            fishProduct.price.convertCurrencyFormat()
                        ),
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 12.sp,
                            color = colorResource(R.color.grey_500),
                        )
                    )
                    Text(
                        stringResource(
                            R.string.stock_convert,
                            "10"
                        ),
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 12.sp,
                            color = colorResource(R.color.grey_500),
                        )
                    )

                }
            }
            Row(
                modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        sheetScope.launch {
                            changePriceSheetState.show()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorResource(R.color.blue),
                    ),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    Text(
                        "Ubah Harga",
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                }

                OutlinedButton(
                    onClick = {
                        sheetScope.launch {
                            changeStockSheetState.show()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorResource(R.color.blue),
                    ),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    Text(
                        "Ubah Stok",
                        style = TextStyle(
                            fontFamily = fonts,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                }

                IconButton(onClick = {
                    sheetScope.launch {
                        moreSheetState.show()
                    }
                }) {
                    Icon(Icons.Outlined.MoreVert, contentDescription = "Localized description")
                }
            }
        }
    }
    BottomSheetMore(sheetScope, moreSheetState, fishProduct, funcEdit, funcDelete)
    BottomSheetChangePrice(sheetScope, changePriceSheetState, fishProduct)
    BottomSheetChangeStock(sheetScope, changeStockSheetState, fishProduct)
}

@Preview(showBackground = true)
@Composable
fun FishProductItemPreview() {
    val menuModel = MenuModel(
        id_fish = "dummy",
        photo_url = "https://cdn.discordapp.com/attachments/888781658566848532/1108666128936484905/img_product_placeholder.png",
        name = "Ikan Tuna",
        price = "Rp. 97.000/kg",
        weight = 10,
        stock = 100,
    )

    Mdc3Theme {
        FishProductItem(
            fishProduct = menuModel,
            funcEdit = null,
            funcDelete = null,
        )
    }
}