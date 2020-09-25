package com.angel.daily_heros.ui.main.qr.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class VisitHistoryViewModel @Inject constructor() : ViewModel(), VisitorPageActionListener {

    private val _visitorPlaceModels: MutableLiveData<List<VisitorPlaceModel>> = MutableLiveData(
        listOf(
            VisitorPlaceModel(
                "김밥천국 회기점",
                true,
                listOf(
                    VisitorModel(
                        "안국현",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "김기명",
                        38.5f,
                        "5분전"
                    ), VisitorModel(
                        "김노을",
                        36.5f,
                        "5분전"
                    )
                )
            ),
            VisitorPlaceModel(
                "시간의향기 이문점",
                false,
                listOf(
                    VisitorModel(
                        "서상원",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "김응진",
                        36.5f,
                        "5분전"
                    ), VisitorModel(
                        "조현욱",
                        38.0f,
                        "5분전"
                    ), VisitorModel(
                        "유재주",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "주창환",
                        36.5f,
                        "20분 전"
                    ), VisitorModel(
                        "김지아",
                        36.5f,
                        "1시간 전"
                    )
                )
            ),
            VisitorPlaceModel(
                "육쌈냉면 외대점",
                false,
                listOf(
                    VisitorModel(
                        "김라헬",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "이화랑",
                        37.9f,
                        "20분 전"
                    ), VisitorModel(
                        "김병우",
                        36.5f,
                        "1시간 전"
                    )
                )
            )
        )
    )
    val visitorPlaceModels: LiveData<List<VisitorPlaceModel>> = _visitorPlaceModels


    val visitorModels: LiveData<List<VisitorModel>> =
        Transformations.map(visitorPlaceModels) { placeModel ->
            placeModel.find { it.checked }?.visitors
        }

    override fun onSelectPlaceTag(index: Int) {
        _visitorPlaceModels.value =
            visitorPlaceModels.value?.mapIndexed { position, visitorPlaceModel ->
                VisitorPlaceModel(
                    visitorPlaceModel.place,
                    index == position,
                    visitorPlaceModel.visitors
                )
            }
    }


}

interface VisitorPageActionListener {
    fun onSelectPlaceTag(index: Int)
}


data class VisitorPlaceModel(
    val place: String,
    val checked: Boolean,
    val visitors: List<VisitorModel>
)


data class VisitorModel(
    val name: String,
    val temperature: Float,
    val checkInTime: String,
    val pbType: PbConImageType = PbConImageType.getRandomPbCon()
)