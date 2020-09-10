package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.user.Item
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularityApi {
    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<List<Item>>

    @GET("users/{username}/following")
    fun getUserFollowings(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<List<Item>>
}