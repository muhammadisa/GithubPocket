package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.user.GitUsers
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("search/users")
    fun getUser(@Query("q") query: String): Single<GitUsers>
}