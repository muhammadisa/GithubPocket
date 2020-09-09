package com.xoxoer.gitpocket.network.user

import com.xoxoer.gitpocket.models.User
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserApi {
    @GET("users/")
    fun getUser(): Flowable<List<User>>
}