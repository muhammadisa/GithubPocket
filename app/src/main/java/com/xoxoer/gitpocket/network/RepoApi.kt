package com.xoxoer.gitpocket.network

import com.xoxoer.gitpocket.models.repo.GitRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {
    @GET("users/{username}/repos")
    fun getUser(@Path("username") username: String): Single<List<GitRepo>>
}