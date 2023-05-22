package id.fishku.fisherseller.presentation.ui.analysis.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherAnalysisActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mdc3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherAnalysisScreen()
                }
            }
        }
    }
}