/*
 * Copyright 2023 by Patryk Goworowski and Patrick Michalik.
 * https://github.com/patrykandpatrick/vico
 */

package id.fishku.fisherseller.presentation.ui.analysis.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.core.component.shape.Shapes
import id.fishku.fisherseller.R

@Composable
internal fun rememberLegend(labelText: String) = verticalLegend(
    items = listOf(
        verticalLegendItem(
            icon = shapeComponent(Shapes.pillShape, colorResource(R.color.blue)),
            label = textComponent(
                color = colorResource(R.color.blue),
                textSize = 12.sp,
            ),
            labelText = labelText,
        )
    ),
    iconSize = 8.dp,
    iconPadding = 10.dp,
    spacing = 4.dp,
    padding = dimensionsOf(top = 8.dp),
)