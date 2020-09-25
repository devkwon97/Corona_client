package com.angel.daily_heros.ui.main.history

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.HistoryFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.ui.main.MainTabsViewModel
import com.angel.daily_heros.util.EventObserver
import com.angel.daily_heros.util.activityViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.time.seconds

class HistoryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainViewModel: MainTabsViewModel

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: HistoryFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            HistoryFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = historyViewModel
            }

        HistoryViewModel.back.observe(viewLifecycleOwner, EventObserver {
            historyViewModel.modeBack()
        })

        historyViewModel.mode.observe(viewLifecycleOwner, Observer {
            if (it == HistoryPageMode.WHITE_BOARD) mainViewModel.showBack()
            else mainViewModel.hideBack()
        })

        mainViewModel.selectTabIndex.observe(viewLifecycleOwner, Observer {
            if (historyViewModel.mode.value == HistoryPageMode.WHITE_BOARD && it == 1) mainViewModel.showBack()
            else mainViewModel.hideBack()
        })

        return binding.root
    }


}

@BindingAdapter("history_items", "action_listener")
fun setHistoryItems(
    view: RecyclerView,
    items: List<HistoryModel>?,
    listener: HistoryActionListener
) {

    var historyAdapter = (view.adapter as? HistoryAdapter)

    if (historyAdapter == null) {
        historyAdapter = HistoryAdapter(listener)
        view.adapter = historyAdapter
    }

    historyAdapter.submitList(items.orEmpty())
}

@BindingAdapter("android:glide_src")
fun setImage(view: ImageView, url: String) {
    if (url.isBlank()) return
    Glide.with(view).load(url).into(view)
}


@BindingAdapter("android:glide_profile_src")
fun setProfileImage(view: ImageView, url: String) {
    if (url.isBlank()) return
    view.background = ShapeDrawable(OvalShape())
    view.clipToOutline = true
    Glide.with(view).load(url).into(view)
}
