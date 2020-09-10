package com.xoxoer.gitpocket.ui.user

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.user.GitUsers
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import com.xoxoer.gitpocket.util.apisingleobserver.Error
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val lifemark: Lifemark,
    private val userRepository: UserRepository
) : ViewModel(), ViewModelContract {

    var searchQuery = ObservableField("")
    var page = ObservableField(1)
    var append = ObservableBoolean(false)

    // users live data
    private val _usersSuccess = MutableLiveData<GitUsers>()
    val usersSuccess: LiveData<GitUsers>
        get() = _usersSuccess

    override var valid = ObservableBoolean()
    override val isLoading = MutableLiveData<Boolean>()
    override val error = ObservableField<Boolean>()
    override val errorReason = ObservableField<String>()

    private fun <T> errorDispatcher(
        errorReason: String,
        targetMutable: MutableLiveData<T>
    ) {
        this.error.set(true)
        this.errorReason.set(errorReason)
        targetMutable.value = null
        Log.e("ERROR_REASON", this.errorReason.get()!!)
    }

    private fun onStart() {
        isLoading.value = true
    }

    private fun onFinish() {
        isLoading.value = false
    }

    private fun <T> handler(targetMutable: MutableLiveData<T>) =
        object : ApiSingleObserver<T>(CompositeDisposable()) {
            override fun onResult(data: T) {
                targetMutable.value = data
            }

            override fun onError(e: Error) {
                when (lifemark.isNetworkConnected()) {
                    true -> errorDispatcher(
                        e.message,
                        targetMutable
                    )
                    false -> errorDispatcher(
                        "No Internet Connection",
                        targetMutable
                    )
                }
            }
        }

    fun retrieveUsers() {
        userRepository.getUsers(
            searchQuery.get()!!,
            10,
            page.get()!!,
            { onStart() },
            { onFinish() },
            handler(_usersSuccess)
        )
    }
}