package id.fishku.fisherseller.presentation.ui.notification

import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.fishku.fisherseller.databinding.StockNotificationItemBinding
import id.fishku.fishersellercore.model.MenuModel



class NotificationAdapter :
    ListAdapter<MenuModel, NotificationAdapter.WordViewHolder>(WordsComparator()) {

    private var onItemClickListener: ((MenuModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (MenuModel) -> Unit) {
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
        fun bind(data: MenuModel) {
            binding.tvTitleNotification.text =
                String.format("\"Perhatian : Stok %s Anda hampir habis!\"", data.name)
            binding.tvDescNotification.text = String.format(
                "Stok %s anda tersisa %d kg. Segera lakukan penambahan persediaan",
                data.name,
                data.weight
            )
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<MenuModel>() {
        override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
            return oldItem.name == newItem.name
        }
    }
}