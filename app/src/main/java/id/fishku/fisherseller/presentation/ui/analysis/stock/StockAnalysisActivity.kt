package id.fishku.fisherseller.presentation.ui.analysis.stock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import dagger.hilt.android.AndroidEntryPoint
import id.fishku.fisherseller.seller.services.SessionManager
import javax.inject.Inject

@AndroidEntryPoint
class StockAnalysisActivity : ComponentActivity (){
    @Inject
    lateinit var prefs: SessionManager
    private val viewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idSeller = prefs.getUser().id
        viewModel.getListFish(idSeller!!).observe(this){ res ->
            setContent {
                Mdc3Theme{
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    ) {
                        StockAnalysisScreen(res)
                    }
                }
            }
        }
    }
}