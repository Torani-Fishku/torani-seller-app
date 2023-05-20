package id.fishku.fisherseller.presentation.ui.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import id.fishku.fisherseller.R
import id.fishku.fisherseller.databinding.ActivityFishDetailBinding
import id.fishku.fisherseller.presentation.ui.add.AddFActivity
import id.fishku.fisherseller.seller.services.SessionManager
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.util.Constants

class FishDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFishDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFishDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val detailFish = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(Constants.SEND_MENU_TO_EDIT, MenuModel::class.java)
        } else {
            intent.getParcelableExtra(Constants.SEND_MENU_TO_EDIT)
        }

        var prefs = SessionManager(this)
        val idSeller = prefs.getUser()

        binding.nameFishDetail.text = detailFish?.name
        binding.priceFish.text = "Rp ${detailFish?.price}/kg"
        binding.stockFish.text = "Tersedia ${detailFish?.weight} kg"
        binding.tvDescDetail.text = "${detailFish?.name} 100% segar"
        binding.ivFish.load(Constants.URL_IMAGE + detailFish?.photo_url) {
            crossfade(true)
            placeholder(R.drawable.baseline_image_24)
        }
        binding.sellerName.text = idSeller.name
        binding.tvLocation.text = idSeller.location

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, AddFActivity::class.java)
            intent.putExtra(Constants.SEND_MENU_TO_EDIT, detailFish)
            startActivity(intent)
        }

    }
}