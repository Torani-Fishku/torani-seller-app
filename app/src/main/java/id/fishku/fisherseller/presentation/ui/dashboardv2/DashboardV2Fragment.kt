package id.fishku.fisherseller.presentation.ui.dashboardv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.themeadapter.material3.Mdc3Theme


/**
 * DashboardV2 Fragment
 *
 * Fragment of Dashboard Version 2 Containing the Composable Screen
 */
class DashboardV2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Mdc3Theme{
                    DashboardV2Screen()
                }
            }
        }
    }
}