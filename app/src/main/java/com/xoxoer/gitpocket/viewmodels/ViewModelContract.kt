package com.xoxoer.gitpocket.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

interface ViewModelContract {
    val isLoading: MutableLiveData<Boolean>
    val error: ObservableField<Boolean>
    val errorReason: ObservableField<String>
}