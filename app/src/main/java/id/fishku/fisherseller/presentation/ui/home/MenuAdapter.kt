package id.fishku.fisherseller.presentation.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import id.fishku.fisherseller.compose.utils.UiState
import id.fishku.fisherseller.databinding.ItemMenuBinding
import id.fishku.fisherseller.presentation.ui.home.component.FishProductItem
import id.fishku.fishersellercore.model.MenuModel
import id.fishku.fishersellercore.response.MessageResponse

/**
 * Menu adapter
 *
 * @property context
 * @constructor Create empty Menu adapter
 */
class MenuAdapter(private val context: Context) :
    ListAdapter<MenuModel, MenuAdapter.ViewHolder>(differCallback) {

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MenuModel>() {
            override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem.id_fish == newItem.id_fish

            override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem, listener, listenerDel, listenerSaveEdit)
        holder.itemView.setOnClickListener { listenerClick?.invoke(getItem(position)) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @OptIn(ExperimentalMaterial3Api::class)
        fun bind(
            data: MenuModel,
            listener: ((MenuModel) -> Unit)?,
            listenerDel: ((MenuModel) -> Unit)?,
            listenerSaveEdit: ((uiState: UiState<MessageResponse>) -> Unit)?
        ) {
            with(binding) {
                cvItemMenu.setContent {
                    Mdc3Theme {
                        FishProductItem(
                            fishProduct = data,
                            funcEdit = listener,
                            funcDelete = listenerDel,
                            funcSaveEdit = listenerSaveEdit
                        )
                    }
                }
            }
        }
    }

    private var listener: ((MenuModel) -> Unit)? = null
    fun setOnItemClick(listener: (MenuModel) -> Unit) {
        this.listener = listener
    }

    private var listenerDel: ((MenuModel) -> Unit)? = null
    fun setOnDelClick(listener: (MenuModel) -> Unit) {
        this.listenerDel = listener
    }

    private var listenerSaveEdit: ((uiState: UiState<MessageResponse>) -> Unit)? = null
    fun setOnSaveEditClick(listener: (uiState: UiState<MessageResponse>) -> Unit) {
        this.listenerSaveEdit = listener
    }

    private var listenerClick : ((MenuModel) -> Unit)? = null
    fun setOnItemClickListener(listener: (MenuModel) -> Unit){
        this.listenerClick = listener
    }
}