package com.angel.weiserqrassetmanagerforsekr.util

import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter


class EditTextUtils {

    lateinit var textWatcher: TextWatcher


    fun getTextWatcherIns(listner: TextWatcherListener): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                listner.beforeTextChanged(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                listner.onTextChanged(s, start, before, count)

            }

            override fun afterTextChanged(s: Editable) {
                listner.afterTextChanged(s)
            }
        }

    }

    fun getafterTextChangeWatcher(listner: (s: Editable) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable) {
                listner(s)
            }
        }

    }

    fun getTextFocusListner(listner: (view: View, hasFocus: Boolean) -> Unit): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { view, b ->
            listner(view, b)
        }
    }


}

interface TextWatcherListener {
    fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
    fun afterTextChanged(s: Editable)
}

interface afterTextChangeListener {
    fun afterTextChanged(s: Editable)
}

interface FocusListener {
    fun onFocusChange(view: View, hasFocus: Boolean)

}

@BindingAdapter("textChangedListener")
fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
    editText.addTextChangedListener(textWatcher)
}