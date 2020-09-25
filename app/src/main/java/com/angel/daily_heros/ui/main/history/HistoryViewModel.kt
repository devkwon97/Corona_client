package com.angel.daily_heros.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angel.daily_heros.ui.main.MainTabsViewModel
import com.angel.daily_heros.util.Event
import javax.inject.Inject

class HistoryViewModel @Inject constructor() : ViewModel(), HistoryActionListener {

    private val _userName: MutableLiveData<String> = MutableLiveData("아무개")
    val userName: LiveData<String> = _userName

    private val _historyModels: MutableLiveData<List<HistoryModel>> = MutableLiveData(
        listOf(
            HistoryModel(
                "https://cdn.pixabay.com/photo/2019/05/04/15/24/art-4178302_960_720.jpg",
                "조한진",
                "김밥천국평양점",
                "1일전 방문"
            ),
            HistoryModel(
                "https://cdn.pixabay.com/photo/2020/05/15/11/49/pet-5173354_960_720.jpg",
                "장도윤",
                "김밥천국부산점",
                "3일전 방문"
            ),
            HistoryModel(
                "https://cdn.pixabay.com/photo/2020/05/22/07/46/model-5204225_960_720.jpg",
                "추지효",
                "김밥천국어딘가",
                "4일전 방문"
            ),
            HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/15/11/22/raspberry-5407356_960_720.jpg",
                "이가연",
                "김밥천국안산점",
                "9일전 방문"
            ), HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/15/09/37/smilies-5407113_960_720.jpg",
                "권경민",
                "김밥천국스프링점",
                "11일전 방문"
            )
            , HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/08/05/31/gray-cat-5382617_960_720.jpg",
                "김아무개",
                "버거킹",
                "22일전 방문"
            ),
            HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/11/19/51/snail-5395186_960_720.jpg",
                "홍길동",
                "크로스핏",
                "40일전 방문"
            ),
            HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/15/11/10/landscape-5407341_960_720.jpg",
                "이우진",
                "우리집",
                "51일전 방문"
            ), HistoryModel(
                "https://cdn.pixabay.com/photo/2020/07/10/08/16/sunflower-5389943_960_720.jpg",
                "현우진",
                "천안농협",
                "70일전 방문"
            )
        )
    )
    val historyModels: LiveData<List<HistoryModel>> = _historyModels

    private val _mode: MutableLiveData<HistoryPageMode> = MutableLiveData(HistoryPageMode.LIST)
    val mode: LiveData<HistoryPageMode> = _mode


    private val _whiteBoardItem: MutableLiveData<HistoryModel> = MutableLiveData(
        HistoryModel(
            "",
            "",
            "",
            ""
        )
    )
    val whiteBoardItem: LiveData<HistoryModel> = _whiteBoardItem


    override fun onClickHistory(position: Int) {
        _whiteBoardItem.value = historyModels.value?.get(position)
        _mode.value = HistoryPageMode.WHITE_BOARD
    }

    fun modeBack() {
        _mode.value = HistoryPageMode.LIST
    }


    companion object {
        val _back: MutableLiveData<Event<Unit>> = MutableLiveData()
        val back: LiveData<Event<Unit>> = _back
    }

}


data class HistoryModel(
    val profile: String,
    val name: String,
    val place: String,
    val visitTime: String
)

enum class HistoryPageMode {
    LIST, WHITE_BOARD
}