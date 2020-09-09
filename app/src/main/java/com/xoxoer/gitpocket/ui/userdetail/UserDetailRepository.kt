package com.xoxoer.gitpocket.ui.userdetail

import com.xoxoer.gitpocket.models.userdetail.GitUserDetail
import com.xoxoer.gitpocket.network.UserDetailApi
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val userDetailApi: UserDetailApi
) {
    fun getUserDetail(
        username: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<GitUserDetail>
    ) {
        userDetailApi.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }
}