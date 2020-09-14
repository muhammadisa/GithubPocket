package com.xoxoer.gitpocket.ui.repo

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.repo.GitRepo
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import javax.inject.Inject

class RepoViewModel @Inject constructor(
    application: Application,
    lifemark: Lifemark,
    private val repoRepository: RepoRepository
) : ViewModel(), ViewModelContract {

    private val pageLength = 10

    var userName = ObservableField("")
    var page = ObservableField(1)
    var append = ObservableBoolean(false)

    // repositories live data
    private val _userRepositorySuccess = MutableLiveData<List<GitRepo>>()
    val userRepositorySuccess: LiveData<List<GitRepo>>
        get() = _userRepositorySuccess

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

    fun retrieveUserRepository() {
        repoRepository.getUserRepository(
            userName.get()!!,
            pageLength,
            page.get()!!,
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_userRepositorySuccess)
        )
    }

}