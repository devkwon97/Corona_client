package com.angel.daily_heros.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.HistoryItemBinding


internal class HistoryAdapter(private val listener: HistoryActionListener) :
    ListAdapter<HistoryModel, HistoryModelViewHolder>(
        HistoryModelDiff
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryModelViewHolder {
        return HistoryModelViewHolder(
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: HistoryModelViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

internal object HistoryModelDiff : DiffUtil.ItemCallback<HistoryModel>() {
    override fun areItemsTheSame(
        oldItem: HistoryModel,
        newItem: HistoryModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: HistoryModel,
        newItem: HistoryModel
    ): Boolean {
        return oldItem.place == newItem.place && oldItem.visitTime == newItem.visitTime
    }

}


internal class HistoryModelViewHolder(
    val binding: HistoryItemBinding,
    val listener: HistoryActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: HistoryModel,
        index: Int
    ) {
        binding.apply {
            binding.model = data
            binding.actionListener = listener
            position = index
        }

    }
}

interface HistoryActionListener {
    fun onClickHistory(position: Int)
}

