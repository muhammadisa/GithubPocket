package com.xoxoer.gitpocket.ui.user

import com.xoxoer.gitpocket.models.user.GitUsers
import com.xoxoer.gitpocket.network.user.UserApi
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {
    fun getUsers(
        query: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<GitUsers>
    ) {
        userApi.getUser(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }
}