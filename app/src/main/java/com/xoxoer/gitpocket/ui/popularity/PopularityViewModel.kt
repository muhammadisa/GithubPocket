package com.xoxoer.gitpocket.ui.popularity

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.user.Item
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler
import com.xoxoer.gitpocket.viewmodels.ViewModelContract
import com.xoxoer.lifemarklibrary.Lifemark
import javax.inject.Inject

class PopularityViewModel @Inject constructor(
    application: Application,
    lifemark: Lifemark,
    private val popularityRepository: PopularityRepository
) : ViewModel(), ViewModelContract {

    private val pageLength = 10

    var userName = ObservableField("")
    var page = ObservableField(1)
    var append = ObservableBoolean(false)
    var mode = ObservableField<String>()

    // users live data
    private val _userPopularitySuccess = MutableLiveData<List<Item>>()
    val userPopularitySuccess: LiveData<List<Item>>
        get() = _userPopularitySuccess

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

    fun retrieveUserFollowers() {
        popularityRepository.getUserFollowers(
            userName.get()!!,
            pageLength,
            page.get()!!,
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_userPopularitySuccess)
        )
    }

    fun retrieveUserFollowings() {
        popularityRepository.getUserFollowings(
            userName.get()!!,
            pageLength,
            page.get()!!,
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_userPopularitySuccess)
        )
    }

}