package id.fishku.fisherseller.presentation.ui.dashboardv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import dagger.hilt.android.AndroidEntryPoint
import id.fishku.fisherseller.seller.services.SessionManager
import javax.inject.Inject

/**
 * DashboardV2 Fragment
 *
 * Fragment of Dashboard Version 2 Containing the Composable Screen
 */
@AndroidEntryPoint
class DashboardV2Fragment : Fragment() {
    @Inject
    lateinit var prefs: SessionManager
    private val viewModel: DashboardV2ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            val idSeller = prefs.getUser().id
            viewModel.getListFish(idSeller!!).observe(viewLifecycleOwner){ res ->
                setContent {
                    Mdc3Theme{
                        DashboardV2Screen(res)
                    }
                }
            }

        }
    }

}