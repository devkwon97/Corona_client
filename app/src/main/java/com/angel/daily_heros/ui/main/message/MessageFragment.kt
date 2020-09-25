package com.angel.daily_heros.ui.main.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.MessageFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.ui.main.MainTabsViewModel
import com.angel.daily_heros.ui.main.history.HistoryPageMode
import com.angel.daily_heros.util.EventObserver
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MessageFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainViewModel: MainTabsViewModel

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var binding: MessageFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            MessageFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = messageViewModel
                actionListener = messageViewModel
            }

        MessageViewModel.back.observe(viewLifecycleOwner, EventObserver {
            messageViewModel.modeBack()
        })


        messageViewModel.mode.observe(viewLifecycleOwner, Observer {
            if (it == MessagePageMode.VIEW) mainViewModel.showBack()
        })

        mainViewModel.selectTabIndex.observe(viewLifecycleOwner, Observer {
            if ((messageViewModel.mode.value == MessagePageMode.VIEW || messageViewModel.mode.value == MessagePageMode.WHITE_BOARD) && it == 2) mainViewModel.showBack()
        })

        return binding.root
    }


}


@BindingAdapter("owner_items", "action_listener")
fun setOwnerItems(
    view: RecyclerView, items: List<OwnerMessageModel>?,
    listener: MessageActionListener
) {

    var ownerMsgAdapter = (view.adapter as? OwnerMsgAdapter)

    if (ownerMsgAdapter == null) {
        ownerMsgAdapter = OwnerMsgAdapter(listener)
        view.adapter = ownerMsgAdapter
    }

    ownerMsgAdapter.submitList(items.orEmpty())
}
