package com.xoxoer.gitpocket.ui.popularity

import com.xoxoer.gitpocket.models.user.Item
import com.xoxoer.gitpocket.network.PopularityApi
import com.xoxoer.gitpocket.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularityRepository @Inject constructor(
    private val popularityApi: PopularityApi
) {
    fun getUserFollowers(
        username: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<List<Item>>
    ) {
        popularityApi.getUserFollowers(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }

    fun getUserFollowings(
        username: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<List<Item>>
    ) {
        popularityApi.getUserFollowings(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { }
            .subscribe(handler)
    }
}