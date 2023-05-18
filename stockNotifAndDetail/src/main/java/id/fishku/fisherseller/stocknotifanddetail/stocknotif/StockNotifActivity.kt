package id.fishku.fisherseller.stocknotifanddetail.stocknotif

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.fishku.fisherseller.stocknotifanddetail.R
import id.fishku.fisherseller.stocknotifanddetail.adapter.NotificationAdapter
import id.fishku.fisherseller.stocknotifanddetail.databinding.ActivityStockNotifBinding
import id.fishku.fisherseller.stocknotifanddetail.detail.FishDetailActivity
import id.fishku.fisherseller.stocknotifanddetail.model.Fish



class StockNotifActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStockNotifBinding
    private val listNotif = ArrayList<Fish>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.rvNotification.setHasFixedSize(true)
        listNotif.addAll(getListNotif())

        binding.rvNotification.layoutManager = LinearLayoutManager(this)
        val adapter = NotificationAdapter()
        binding.rvNotification.adapter = adapter
        adapter.submitList(listNotif)

        adapter.setOnItemClickListener { fish ->
            Intent(this, FishDetailActivity::class.java).also {
                it.putExtra(FishDetailActivity.DETAIL, fish)
                startActivity(it)
            }

        }

        binding.btnBack.setOnClickListener{
//            val intent = Intent(this, DashboardActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
//            finish()
        }
    }

    private fun getListNotif() : ArrayList<Fish>{
        val name = resources.getStringArray(R.array.name_fish)
        val stock = resources.getIntArray(R.array.stock_fish)
        val price = resources.getIntArray(R.array.price)
        val desc = resources.getStringArray(R.array.decs)
        val sellerName = resources.getStringArray(R.array.seller)
        val tpiLoc = resources.getStringArray(R.array.tpi_loc)
        val location = resources.getStringArray(R.array.location)


        val listNotification = ArrayList<Fish>()
        for (i in name.indices){
            if (stock[i] < 10) {
                val notification = Fish(
                    name[i],
                    stock[i],
                    price[i],
                    desc[i],
                    sellerName[i],
                    tpiLoc[i],
                    location[i]
                )
                listNotification.add(notification)
            }
        }
        return listNotification
    }


}