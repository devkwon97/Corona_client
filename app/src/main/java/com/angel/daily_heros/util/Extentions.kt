package com.angel.daily_heros.util

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Allows calls like
 *
 * `supportFragmentManager.inTransaction { add(...) }`
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

// region ViewModels

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)


/**
 * For Actvities, allows declarations like
 * ```
 * hideNavigationUI()
 * ```
 */

//fun FragmentActivity.hideNavigationUI() {
//    val decorView = window.decorView
//    decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//}

fun FragmentActivity.hideSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)

}

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider()
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    activity: FragmentActivity?
) =
    activity?.run {
        ViewModelProviders.of(activity).get(VM::class.java)
    } ?: throw Exception("Invalid activity")

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(parentFragment!!, provider).get(VM::class.java)


/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}


class LoadingLiveData : MediatorLiveData<Boolean>() {
    val mObservers = ArrayList<MutableLiveData<Result<Any>>>()

    fun <T> addSource(livedata: MutableLiveData<Result<Any>>) {
        mObservers.add(livedata)
        Log.d(TAG, "Source added to loading checker")
        addSource(livedata) {
            this.value = checkLoading()
            Log.d(TAG, "Loading ${this.value}")
        }
    }

    private fun checkLoading(): Boolean {
        return mObservers.any {
            Log.d(TAG, "Check loading")
            it.value == Result.Loading
        }
    }

    companion object {
        val TAG = "LoadingLiveData"
    }
}

inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}

//fun <T> List<T>.copy(): List<T> {
//    return listOf(*this.map { model -> model as data class }.toTypedArray())
//}


//private fun navigateToAddNewTask() {
//    val action = TasksFragmentDirections
//        .actionTasksFragmentToAddEditTaskFragment(
//            null,
//            resources.getString(R.string.add_task)
//        )
//    findNavController().navigate(action)
//}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}


fun Int.dpToPx(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun String.isValidateEmail() = this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidatePassword() = this.isNotBlank() && this.length >= 8

fun String.isValidatePhoneNumber() = this.isNotBlank() && Patterns.PHONE.matcher(this).matches() && this.length >= 10

//Todo:: need number regex
fun String.validatePhoneNumber() = this.length == 10 || this.length == 11

fun String.validateCertify() = this.length == 4

fun Any.TAG(): String = this.javaClass.name ?: "Unknown"

/**
 * Returns `true` if enum T contains an entry with the specified name.
 */
inline fun <reified T : Enum<T>> enumContains(name: String): Boolean {
    return enumValues<T>().any { it.name == name}
}