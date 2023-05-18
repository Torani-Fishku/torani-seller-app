package id.fishku.fisherseller.presentation.ui.home.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.presentation.ui.dashboardv2.theme.fonts
import id.fishku.fishersellercore.model.MenuModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetMore(
    moreSheetScope: CoroutineScope,
    moreSheetState: SheetState,
    fishProduct: MenuModel,
    funcEdit: ((MenuModel) -> Unit)?,
    funcDelete: ((MenuModel) -> Unit)?,
    //TODO: Add function to see the detail preview page of product
) {
    if (moreSheetState.isVisible) {
        ModalBottomSheet(
            sheetState = moreSheetState,
            onDismissRequest = {
                moreSheetScope.launch {
                    moreSheetState.hide()
                }
            },
            containerColor = colorResource(R.color.white)
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.white)
                ),
                title = {
                    Text(
                        "Pilihan Lainnya",
                        style = TextStyle(
                            fontFamily = fonts,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.black),
                            fontSize = 20.sp,
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {
                        moreSheetScope.launch {
                            moreSheetState.hide()
                        }
                    }) {
                        Icon(Icons.Rounded.Close, contentDescription = "Cancel")
                    }
                }
            )
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor =  colorResource(R.color.white)
                ),
                headlineContent = { OptionText("Edit barang") },
                leadingContent = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = "Localized description",
                    )
                },
                modifier = Modifier.clickable {
                    funcEdit?.let {
                        funcEdit(fishProduct)
                    }
                }
            )
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor =  colorResource(R.color.white)
                ),
                headlineContent = { OptionText("Lihat tampilan pembeli") },
                leadingContent = {
                    Icon(
                        Icons.Outlined.Visibility,
                        contentDescription = "Localized description",
                    )
                },
                modifier = Modifier.clickable {
                    //TODO: Call function to see the detail preview page of product
                }
            )
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor =  colorResource(R.color.white)
                ),
                headlineContent = { OptionText("Hapus barang") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Localized description",
                        tint = colorResource(R.color.red_error),
                    )
                },
                modifier = Modifier.clickable {
                    funcDelete?.let {
                        funcDelete(fishProduct)
                    }
                }
            )
        }
    }
}

@Composable
fun OptionText(text: String) {
    Text(
        text, style = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            color = colorResource
                (if (text == "Hapus Barang") R.color.red_error else R.color.black),
            fontSize = 16.sp,
        )
    )
}
