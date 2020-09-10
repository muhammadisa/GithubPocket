package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.repo.GitRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {
    @GET("users/{username}/repos")
    fun getUserRepository(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<List<GitRepo>>
}