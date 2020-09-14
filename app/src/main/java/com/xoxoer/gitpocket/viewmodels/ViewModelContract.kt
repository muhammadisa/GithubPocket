package com.xoxoer.gitpocket.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.xoxoer.gitpocket.util.apisingleobserver.RxSingleHandler

interface ViewModelContract {
    val isLoading: MutableLiveData<Boolean>
    val error: ObservableField<Boolean>
    val errorReason: ObservableField<String>
}