package com.angel.daily_heros.ui.main.qr.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.R
import com.angel.daily_heros.databinding.VisitorHistoryFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.ui.main.MainTabsViewModel
import com.angel.daily_heros.ui.main.history.HistoryPageMode
import com.angel.daily_heros.util.activityViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class VisitHistoryFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainViewModel: MainTabsViewModel


    private lateinit var visitViewModel: VisitHistoryViewModel
    private lateinit var binding: VisitorHistoryFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        visitViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            VisitorHistoryFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = visitViewModel
                actionListener = visitViewModel
                scope = lifecycleScope
            }

        mainViewModel.selectTabIndex.observe(viewLifecycleOwner, Observer {
            if (it == 0) mainViewModel.hideBack()
        })


        return binding.root
    }
}


@BindingAdapter("visitor_place_tag_items", "action_listener")
fun setVisitorPlaceTagItems(
    view: RecyclerView,
    items: List<VisitorPlaceModel>?,
    actionListener: VisitorPageActionListener
) {

    var visitorPlaceTagListAdapter = (view.adapter as? VisitorPlaceTagListAdapter)
    view.itemAnimator = null
    if (visitorPlaceTagListAdapter == null) {
        visitorPlaceTagListAdapter = VisitorPlaceTagListAdapter(actionListener)
        view.adapter = visitorPlaceTagListAdapter
    }

    visitorPlaceTagListAdapter.submitList(items.orEmpty())
}


@BindingAdapter("visitor_history_items", "life_cycle")
fun setVisitorHistoryItems(
    view: RecyclerView,
    items: List<VisitorModel>?,
    coroutineScope: CoroutineScope
) {

    var visitorHistoryListAdapter = (view.adapter as? VisitorHistoryListAdapter)
    view.itemAnimator = null
    if (visitorHistoryListAdapter == null) {
        visitorHistoryListAdapter = VisitorHistoryListAdapter()
        view.adapter = visitorHistoryListAdapter
    }

    visitorHistoryListAdapter.submitList(items.orEmpty())
    coroutineScope.launch(Dispatchers.Main) {
        view.scrollToPosition(0)
    }

}


@BindingAdapter("android:pb_img")
fun setImageById(view: ImageView, pbType: PbConImageType) {
    Glide.with(view).load(
        when (pbType) {
            PbConImageType.MEN -> R.drawable.pb_man
            PbConImageType.COCALA -> R.drawable.pb_coala
            PbConImageType.MEN_HAND -> R.drawable.pb_man_hand
            PbConImageType.DOG -> R.drawable.pb_dog
            PbConImageType.OLD_MAN -> R.drawable.pb_old_man
            PbConImageType.MERMAID -> R.drawable.pb_mermaid
            PbConImageType.FAIRY -> R.drawable.pb_fairy
            PbConImageType.WOMAN -> R.drawable.pb_women
            PbConImageType.WOMAN_HAT -> R.drawable.pb_women_hat
            PbConImageType.CAT -> R.drawable.pb_cat
            PbConImageType.MAN_HAND_UP -> R.drawable.pb_man_hand_up
            PbConImageType.MONKEY -> R.drawable.pb_monkey
        }
    ).into(view)


}

enum class PbConImageType(number: Int) {
    MEN(0),
    COCALA(1),
    MEN_HAND(2),
    DOG(3),
    OLD_MAN(4),
    MERMAID(5),
    FAIRY(6),
    WOMAN(7),
    WOMAN_HAT(8),
    CAT(9),
    MAN_HAND_UP(10),
    MONKEY(11);

    companion object {
        fun getRandomPbCon(): PbConImageType {
            return values()[(Math.random() * 11).toInt()]
        }
    }
}