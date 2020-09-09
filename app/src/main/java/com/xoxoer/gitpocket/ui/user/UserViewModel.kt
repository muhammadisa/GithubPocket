package com.xoxoer.gitpocket.ui.user

import android.util.Log
import androidx.lifecycle.ViewModel
import com.xoxoer.gitpocket.models.User
import com.xoxoer.gitpocket.network.user.UserApi
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
    val userApi: UserApi
) : ViewModel() {
    init {
        Log.e("FROM_USER_VM", "Hello from user view model")
    }

    fun checkUserApi() {}
}