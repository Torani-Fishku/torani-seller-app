package id.fishku.fisherseller.presentation.ui.notification

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.fishku.fisherseller.R
import id.fishku.fisherseller.databinding.ActivityStockNotifBinding
import id.fishku.fisherseller.otp.core.Status
import id.fishku.fisherseller.presentation.ui.home.HomeFragment
import id.fishku.fisherseller.presentation.ui.home.HomeViewModel
import id.fishku.fisherseller.seller.services.SessionManager
import id.fishku.fishersellercore.model.MenuModel
import javax.inject.Inject

@AndroidEntryPoint
class StockNotifActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStockNotifBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.rvNotification.setHasFixedSize(true)


        binding.rvNotification.layoutManager = LinearLayoutManager(this)
        observableViewModel()

//        adapter.setOnItemClickListener { fish ->
//            Intent(this, FishDetailActivity::class.java).also {
//                it.putExtra(FishDetailActivity.DETAIL, fish)
//                startActivity(it)
//            }
//
//        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    private fun observableViewModel() {
        var prefs = SessionManager(this)
        val idSeller = prefs.getUser().id
        viewModel.getListFish(idSeller!!).observe(this) { res ->
            when (res.status) {
                Status.LOADING -> {
                    loading(true)
                }
                Status.ERROR -> {
                    loading(false)
                }
                Status.SUCCESS -> {
                    loading(false)
                    val lessFish = res.data?.data?.filter { it.weight < 10 }
                    if (lessFish!!.isEmpty())
                        binding.tvNoData.visibility = View.VISIBLE
                    val adapter = NotificationAdapter()
                    binding.rvNotification.adapter = adapter
                    adapter.submitList(lessFish)
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading)
            ""
        else
            ""
    }

    companion object{
        const val MENU_MODEL = "menu_model"
    }

}