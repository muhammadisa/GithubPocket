package com.xoxoer.gitpocket.ui.user

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserActivity : DaggerAppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(UserViewModel::class.java)

        userViewModel.checkUserApi()
    }
}