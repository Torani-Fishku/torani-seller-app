package id.fishku.fisherseller.presentation.ui.dashboardv2.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.fishku.fisherseller.R
import id.fishku.fisherseller.presentation.ui.dashboardv2.theme.fonts

/**
 * TitleAndDivider Composable Component
 *
 * Component to show Title and Divider at Dashboard
 * @param title Title to be displayed
 * @param onClick Optional function to navigate to detail page
 */
@Composable
fun TitleAndDivider(title: String, onClick: (() -> Unit)? = null) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            title,
            style = TextStyle(
                fontFamily = fonts, fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.blue),
            )
        )
        if (onClick != null) {
            Spacer(Modifier.weight(1f))
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "Notification",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }

        if (title == "Analisis Stok") {
            var expanded by remember { mutableStateOf(false) }

            Spacer(Modifier.weight(1f, true))
            Box {
                TextButton(
                    onClick = { expanded = !expanded },
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text("Urutkan", style = TextStyle(fontFamily = fonts, fontSize = 12.sp))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Icon(
                        Icons.Filled.ExpandMore,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Urutkan dari stok paling sedikit",
                                style = TextStyle(
                                    fontFamily = fonts, platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    )
                                )
                            )
                        },
                        onClick = { }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Urutkan dari stok paling banyak",
                                style = TextStyle(fontFamily = fonts)
                            )
                        },
                        onClick = { }
                    )
                }
            }
        }
    }
    if (onClick == null && title != "Analisis Stok") {
        Spacer(modifier = Modifier.height(8.dp))
    }
    Divider(thickness = 1.dp, modifier = Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints.copy(
                // Resize this item's maxWidth by adding DPs to incoming constraints
                maxWidth = constraints.maxWidth + 80.dp.roundToPx()
            )
        )
        layout(placeable.width, placeable.height) {
            // Place this item in the original position
            placeable.place(0, 0)
        }
    })

}