package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.userdetail.GitUserDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface UserDetailApi {
    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Single<GitUserDetail>
}