package com.angel.daily_heros.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angel.daily_heros.R
import com.angel.daily_heros.ui.main.history.HistoryViewModel
import com.angel.daily_heros.ui.main.message.MessageViewModel
import com.angel.daily_heros.util.Event
import javax.inject.Inject

class MainTabsViewModel @Inject constructor(
) : ViewModel(), MainTabsActionListener, TopIconButtonListener {


    private val _tabsModel = MutableLiveData<List<TabModel>>()
    val tabsModel: LiveData<List<TabModel>> = _tabsModel

    private val _selectTabIndex = MutableLiveData<Int>(0)
    val selectTabIndex: LiveData<Int> = _selectTabIndex

    private val _moveQRScanner: MutableLiveData<Event<Unit>> = MutableLiveData()
    val moveQRScanner: LiveData<Event<Unit>> = _moveQRScanner

    private val _showBack: MutableLiveData<Boolean> = MutableLiveData(false)
    override val showBack: LiveData<Boolean> = _showBack

    fun showBack() {
        _showBack.value = true
    }

    fun hideBack() {
        _showBack.value = false
    }


    override fun onClickTab(to: Int) {
        _selectTabIndex.value.let {
            _tabsModel.value = _tabsModel.value!!.mapIndexed { id, item ->
                item.selected = id == to
                item
            }
            _selectTabIndex.value = to
        }
    }


    fun updateModel(): Int {
        //Refresh event for animation.
        val index = tabsModel.value?.indexOf(tabsModel.value?.find { it.selected })
        _selectTabIndex.value = index
        return index ?: 0
    }

    fun createTabModels() {
        _tabsModel.value = listOf(
            TabModel(
                true,
                TabContentFragment.HISTORY_OWNER,
                R.id.fl_main_act_owner_history
            ),
            TabModel(
                false,
                TabContentFragment.MY_HISTORY,
                R.id.fl_main_act_my_history
            ),
            TabModel(
                false,
                TabContentFragment.NOTICE,
                R.id.fl_main_act_notice
            )
        )
    }

    override fun onClickQR() {
        _moveQRScanner.value = Event(Unit)
    }

    override fun onClickPerson() {
    }

    override fun onClickBackButton() {
        val selectedTab = _tabsModel.value?.find { it.selected }
        _showBack.value = false
        when (selectedTab?.fragment) {
            TabContentFragment.MY_HISTORY -> HistoryViewModel._back.value = Event(Unit)
            TabContentFragment.NOTICE -> MessageViewModel._back.value = Event(Unit)
        }
    }

}

interface MainTabsActionListener {
    fun onClickTab(to: Int)
}

data class TabModel(
    var selected: Boolean,
    val fragment: TabContentFragment,
    val id: Int
)


enum class TabContentFragment {
    HISTORY_OWNER, MY_HISTORY, NOTICE
}


interface TopIconButtonListener {
    val showBack: LiveData<Boolean>
    fun onClickBackButton()
    fun onClickQR()
    fun onClickPerson()
}

