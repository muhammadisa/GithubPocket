package com.xoxoer.gitpocket.ui.repo

import com.xoxoer.gitpocket.models.repo.GitRepo
import com.xoxoer.gitpocket.network.RepoApi
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val repoApi: RepoApi
) {

    fun getUserRepository(
        username: String,
        perPage: Int,
        page: Int,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<List<GitRepo>>
    ) {
        repoApi.getUserRepository(username, perPage, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }

}