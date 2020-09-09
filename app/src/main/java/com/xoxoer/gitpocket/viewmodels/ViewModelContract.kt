package com.xoxoer.gitpocket.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

interface ViewModelContract {
    var valid: ObservableBoolean
    val isLoading: MutableLiveData<Boolean>
    val error: ObservableField<Boolean>
    val errorReason: ObservableField<String>
}