package com.angel.daily_heros.ui.main.qr.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.R
import com.angel.daily_heros.databinding.VisitorPlaceTagItemBinding

internal class VisitorPlaceTagListAdapter(private val actionListener: VisitorPageActionListener) :
    ListAdapter<VisitorPlaceModel, VisitorPlaceModelViewHolder>(
        VisitorPlaceModelDiff
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VisitorPlaceModelViewHolder {
        return VisitorPlaceModelViewHolder(
            VisitorPlaceTagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            actionListener
        )
    }

    override fun onBindViewHolder(holder: VisitorPlaceModelViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

internal object VisitorPlaceModelDiff : DiffUtil.ItemCallback<VisitorPlaceModel>() {
    override fun areItemsTheSame(
        oldItem: VisitorPlaceModel,
        newItem: VisitorPlaceModel
    ): Boolean {
        return oldItem.place == newItem.place
    }

    override fun areContentsTheSame(
        oldItem: VisitorPlaceModel,
        newItem: VisitorPlaceModel
    ): Boolean {
        return oldItem.checked == newItem.checked
    }

}


internal class VisitorPlaceModelViewHolder(
    val binding: VisitorPlaceTagItemBinding,
    val listener: VisitorPageActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: VisitorPlaceModel,
        position: Int
    ) {
        binding.apply {
            model = data
            binding.actionListener = listener
            index = position

        }

    }
}


@BindingAdapter("visitor_place_checked")
fun setVisitorTagView(view: TextView, checked: Boolean) {

    when (checked) {
        true -> {
            view.background =
                ResourcesCompat.getDrawable(view.resources, R.drawable.background_blue_border, null)

            view.alpha = 1f
            view.setTextColor(Color.parseColor("#034aff"))
        }
        false -> {
            view.background = ResourcesCompat.getDrawable(
                view.resources,
                R.drawable.background_dark_border,
                null
            )
            view.alpha = 0.38f
            view.setTextColor(Color.parseColor("#000000"))

        }
    }

}



