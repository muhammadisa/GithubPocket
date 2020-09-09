package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.user.Item
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PopularityApi {
    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): Single<List<Item>>

    @GET("users/{username}/following")
    fun getUserFollowings(@Path("username") username: String): Single<List<Item>>
}