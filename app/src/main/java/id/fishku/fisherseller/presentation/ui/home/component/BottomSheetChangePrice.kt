package id.fishku.fisherseller.presentation.ui.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import id.fishku.fisherseller.R
import id.fishku.fisherseller.compose.theme.fonts
import id.fishku.fisherseller.compose.utils.UiState
import id.fishku.fisherseller.presentation.ui.add.AddViewModel
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.requests.AddRequest
import id.fishku.fishersellercore.response.MessageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetChangePrice(
    moreSheetScope: CoroutineScope,
    moreSheetState: SheetState,
    fishProduct: MenuModel,
    funcSaveEdit: ((uiState: UiState<MessageResponse>) -> Unit)?,
) {
    var price by rememberSaveable { mutableStateOf(fishProduct.price) }
    val addViewModel = hiltViewModel<AddViewModel>()

    if (moreSheetState.isVisible) {
        addViewModel.response.collectAsState(UiState.Idle).value.let{ uiState ->
            ModalBottomSheet(
                sheetState = moreSheetState,
                onDismissRequest = {
                    moreSheetScope.launch {
                        moreSheetState.hide()
                    }
                },
                containerColor = colorResource(R.color.white),
                modifier = Modifier.fillMaxWidth().navigationBarsPadding(),
            ) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = colorResource(R.color.white)
                    ),
                    title = {
                        Text(
                            "Ubah Harga",
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
                TextField(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    value = price,
                    onValueChange = { price = it },
                    label = {
                        Text(
                            "Harga per kg (Rp)", style = TextStyle(
                                fontFamily = fonts,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                            )
                        )
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    onClick = {
                        val request = AddRequest(
                            fishProduct.id_fish,
                            fishProduct.name,
                            fishProduct.weight.toString(),
                            "",
                            price,
                            fishProduct.photo_url,
                        )
                        addViewModel.editMenuCompose(fishProduct.id_fish, request)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.blue)
                    )
                ) {
                    Text(
                        "Simpan"
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
            if(uiState is UiState.Error  || uiState is UiState.Success ){
                if (funcSaveEdit != null) {
                    funcSaveEdit(uiState)
                }
                moreSheetScope.launch {
                    moreSheetState.hide()
                }
                addViewModel.setResponseBackToIdle()
            }
        }
    }
}