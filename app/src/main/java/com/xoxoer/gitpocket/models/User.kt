package com.xoxoer.gitpocket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("username")
    @Expose
    val userName: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("phone")
    @Expose
    val phone: String,

    @SerializedName("website")
    @Expose
    val website: String
)