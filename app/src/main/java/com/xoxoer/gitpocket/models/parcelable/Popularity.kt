package com.xoxoer.gitpocket.models.parcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Popularity (
    val popularityMode: String,
    val username: String
): Parcelable