package id.fishku.fisherseller.stocknotifanddetail.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import id.fishku.fisherseller.stocknotifanddetail.R
import id.fishku.fisherseller.stocknotifanddetail.adapter.ImageAdapter
import id.fishku.fisherseller.stocknotifanddetail.databinding.ActivityFishDetailBinding
import id.fishku.fisherseller.stocknotifanddetail.model.Fish
import id.fishku.fisherseller.stocknotifanddetail.stocknotif.StockNotifActivity
import kotlin.math.abs

class FishDetailActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    private lateinit var binding : ActivityFishDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFishDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        init()

        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
            }
        })

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, StockNotifActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        val detailFish = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DETAIL, Fish::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DETAIL)
        }

        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)

        imageList = ArrayList()
        if (detailFish?.name == "Bandeng"){
            imageList.add(R.drawable.bandeng)
            imageList.add(R.drawable.bandeng2)
            imageList.add(R.drawable.bandeng3)
        } else if (detailFish?.name == "Kakap"){
            imageList.add(R.drawable.kakap1)
            imageList.add(R.drawable.kakap2)
            imageList.add(R.drawable.kakap3)
        }
        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.nameFishDetail.setText(detailFish?.name)
        binding.stockFish.setText("Tersisa ${detailFish!!.stock} kg")
        binding.priceFish.setText("Rp " + detailFish.price.toString())
        binding.tvDescDetail.setText(detailFish.desc)
        binding.sellerName.setText(detailFish.sellerName)
        binding.tvLocTpi.setText(detailFish.tpiLoc)
        binding.tvLocation.setText(detailFish.location)

    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    companion object {
        const val DETAIL = "fish_detail"
    }


}