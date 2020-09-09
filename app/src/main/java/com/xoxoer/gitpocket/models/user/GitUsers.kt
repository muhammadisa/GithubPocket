package com.xoxoer.gitpocket.models.user

data class GitUsers(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)