package com.xoxoer.gitpocket.ui.user

import com.xoxoer.gitpocket.models.user.GitUsers
import com.xoxoer.gitpocket.network.UserApi
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {
    fun getUsers(
        query: String,
        perPage: Int,
        page: Int,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<GitUsers>
    ) {
        userApi.getUser(query, perPage, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }
}