package com.angel.daily_heros.ui.main.qr.check

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angel.daily_heros.util.Event
import javax.inject.Inject

class CheckListViewModel @Inject constructor() : ViewModel(), CheckListActionListener {

    private val _contact: MutableLiveData<CheckModel> =
        MutableLiveData(CheckModel(CheckType.CONTACT))
    val contact: LiveData<CheckModel> = _contact

    private val _visit: MutableLiveData<CheckModel> =
        MutableLiveData(CheckModel(CheckType.VISIT))
    val visit: LiveData<CheckModel> = _visit

    private val _symptom: MutableLiveData<CheckModel> =
        MutableLiveData(CheckModel(CheckType.SYMPTOM))
    val symptom: LiveData<CheckModel> = _symptom

    private val _mask: MutableLiveData<CheckModel> =
        MutableLiveData(CheckModel(CheckType.MASK))
    val mask: LiveData<CheckModel> = _mask

    private val _handWash: MutableLiveData<CheckModel> =
        MutableLiveData(CheckModel(CheckType.HAND_WASH))
    val handWash: LiveData<CheckModel> = _handWash

    private val _submit: MutableLiveData<Event<Unit>> = MutableLiveData()
    val submit: LiveData<Event<Unit>> = _submit

    private val _doc: MutableLiveData<Float> = MutableLiveData(36.5f)
    val doc: LiveData<Float> = _doc


    fun setDoc(t: Float) {
        _doc.value = t

    }

    override fun onCheckYes(type: CheckType) {
        when (type) {
            CheckType.CONTACT -> {
                _contact.value = CheckModel(CheckType.CONTACT, AnswerType.YES)
            }
            CheckType.VISIT -> {
                _visit.value = CheckModel(CheckType.CONTACT, AnswerType.YES)
            }
            CheckType.SYMPTOM -> {
                _symptom.value = CheckModel(CheckType.CONTACT, AnswerType.YES)
            }
            CheckType.MASK -> {
                _mask.value = CheckModel(CheckType.CONTACT, AnswerType.YES)
            }
            CheckType.HAND_WASH -> {
                _handWash.value = CheckModel(CheckType.CONTACT, AnswerType.YES)
            }

        }

    }

    override fun onCheckNo(type: CheckType) {
        when (type) {
            CheckType.CONTACT -> {
                _contact.value = CheckModel(CheckType.CONTACT, AnswerType.NO)
            }
            CheckType.VISIT -> {
                _visit.value = CheckModel(CheckType.CONTACT, AnswerType.NO)
            }
            CheckType.SYMPTOM -> {
                _symptom.value = CheckModel(CheckType.CONTACT, AnswerType.NO)
            }
            CheckType.MASK -> {
                _mask.value = CheckModel(CheckType.CONTACT, AnswerType.NO)
            }
            CheckType.HAND_WASH -> {
                _handWash.value = CheckModel(CheckType.CONTACT, AnswerType.NO)
            }

        }
    }

    override fun onClickSubmit() {

        if (contact.value?.answer == AnswerType.DEFAULT) return
        if (visit.value?.answer == AnswerType.DEFAULT) return
        if (symptom.value?.answer == AnswerType.DEFAULT) return
        if (mask.value?.answer == AnswerType.DEFAULT) return
        if (handWash.value?.answer == AnswerType.DEFAULT) return

        _submit.value = Event(Unit)

    }

}

interface CheckListActionListener {
    fun onCheckYes(type: CheckType)
    fun onCheckNo(type: CheckType)
    fun onClickSubmit()
}

data class CheckModel(
    val type: CheckType,
    val answer: AnswerType = AnswerType.DEFAULT
)

enum class CheckType {
    CONTACT, VISIT, SYMPTOM, MASK, HAND_WASH
}

enum class AnswerType {
    YES, NO, DEFAULT
}