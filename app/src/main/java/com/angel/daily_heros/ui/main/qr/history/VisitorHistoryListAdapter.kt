package com.angel.daily_heros.ui.main.qr.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.VisitorHistoryItemBinding
import com.angel.daily_heros.databinding.VisitorPlaceTagItemBinding


internal class VisitorHistoryListAdapter :
    ListAdapter<VisitorModel, VisitorModelViewHolder>(
        VisitorModelDiff
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VisitorModelViewHolder {
        return VisitorModelViewHolder(
            VisitorHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VisitorModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal object VisitorModelDiff : DiffUtil.ItemCallback<VisitorModel>() {
    override fun areItemsTheSame(
        oldItem: VisitorModel,
        newItem: VisitorModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: VisitorModel,
        newItem: VisitorModel
    ): Boolean {
        return oldItem.temperature == newItem.temperature && oldItem.checkInTime == newItem.checkInTime
    }

}


internal class VisitorModelViewHolder(
    val binding: VisitorHistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: VisitorModel
    ) {
        binding.apply {
            binding.model = data
        }

    }
}