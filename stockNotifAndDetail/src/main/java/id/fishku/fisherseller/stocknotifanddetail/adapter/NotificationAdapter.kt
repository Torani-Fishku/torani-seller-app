package id.fishku.fisherseller.stocknotifanddetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fisherseller.stocknotifanddetail.databinding.StockNotificationItemBinding
import id.fishku.fisherseller.stocknotifanddetail.model.Fish



class NotificationAdapter :
    ListAdapter<Fish, NotificationAdapter.WordViewHolder>(WordsComparator()) {

    private var onItemClickListener: ((Fish) -> Unit)? = null

    fun setOnItemClickListener(listener: (Fish) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding =
            StockNotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(getItem(position)) }

    }

    class WordViewHolder(private val binding: StockNotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Fish) {
            binding.tvTitleNotification.text =
                String.format("\"Perhatian : Stok ikan %s Anda hampir habis!\"", data.name)
            binding.tvDescNotification.text = String.format(
                "Stok %s anda tersisa %d kg. Segera lakukan penambahan persediaan",
                data.name,
                data.stock
            )
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Fish>() {
        override fun areItemsTheSame(oldItem: Fish, newItem: Fish): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Fish, newItem: Fish): Boolean {
            return oldItem.name == newItem.name
        }
    }
}