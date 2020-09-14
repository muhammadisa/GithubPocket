package com.xoxoer.gitpocket.ui.user

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.user.GitUsers
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import javax.inject.Inject

class UserViewModel @Inject constructor(
    application: Application,
    lifemark: Lifemark,
    private val userRepository: UserRepository
) : ViewModel(), ViewModelContract {

    private val pageLength = 10

    var searchQuery = ObservableField("")
    var page = ObservableField(1)
    var append = ObservableBoolean(false)

    // users live data
    private val _usersSuccess = MutableLiveData<GitUsers>()
    val usersSuccess: LiveData<GitUsers>
        get() = _usersSuccess

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

    fun retrieveUsers() {
        userRepository.getUsers(
            searchQuery.get()!!,
            pageLength,
            page.get()!!,
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_usersSuccess)
        )
    }
}