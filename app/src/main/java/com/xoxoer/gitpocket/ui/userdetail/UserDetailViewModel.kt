package com.xoxoer.gitpocket.ui.userdetail

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.userdetail.GitUserDetail
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import com.xoxoer.gitpocket.util.apisingleobserver.Error
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val lifemark: Lifemark,
    private val userDetailRepository: UserDetailRepository
) : ViewModel(), ViewModelContract {

    var userName = ObservableField("")

    // users live data
    private val _userDetailSuccess = MutableLiveData<GitUserDetail>()
    val userDetailSuccess: LiveData<GitUserDetail>
        get() = _userDetailSuccess

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

    fun retrieveUserDetail() {
        userDetailRepository.getUserDetail(
            userName.get()!!,
            { onStart() },
            { onFinish() },
            handler(_userDetailSuccess)
        )
    }
}