package com.angel.daily_heros.ui.main.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.OwnerMessageItemBinding


internal class OwnerMsgAdapter(private val listener: MessageActionListener) :
    ListAdapter<OwnerMessageModel, OwnerMessageModelViewHolder>(
        OwnerMessageModelDiff
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OwnerMessageModelViewHolder {
        return OwnerMessageModelViewHolder(
            OwnerMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: OwnerMessageModelViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

internal object OwnerMessageModelDiff : DiffUtil.ItemCallback<OwnerMessageModel>() {
    override fun areItemsTheSame(
        oldItem: OwnerMessageModel,
        newItem: OwnerMessageModel
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.place == newItem.place
    }

    override fun areContentsTheSame(
        oldItem: OwnerMessageModel,
        newItem: OwnerMessageModel
    ): Boolean {
        return oldItem.msg == newItem.msg
    }

}


internal class OwnerMessageModelViewHolder(
    val binding: OwnerMessageItemBinding,
    val listener: MessageActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: OwnerMessageModel,
        index: Int
    ) {
        binding.apply {
            binding.model = data
            binding.actionListener = listener
            binding.position = index
        }

    }
}

interface MessageActionListener {
    fun onClickMessage(position: Int)
}

