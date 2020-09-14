package com.xoxoer.gitpocket.ui.userdetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.userdetail.GitUserDetail
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    application: Application,
    lifemark: Lifemark,
    private val userDetailRepository: UserDetailRepository
) : ViewModel(), ViewModelContract {

    var userName = ObservableField("")

    // users live data
    private val _userDetailSuccess = MutableLiveData<GitUserDetail>()
    val userDetailSuccess: LiveData<GitUserDetail>
        get() = _userDetailSuccess

    private val rxSingleHandler = RxSingleHandler(application, lifemark, this)
    override val isLoading = MutableLiveData<Boolean>()
    override val error = ObservableField<Boolean>()
    override val errorReason = ObservableField<String>()

    private fun onStart() {
        isLoading.value = true
    }

    private fun onFinish() {
        isLoading.value = false
    }

    fun retrieveUserDetail() {
        userDetailRepository.getUserDetail(
            userName.get()!!,
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_userDetailSuccess)
        )
    }
}