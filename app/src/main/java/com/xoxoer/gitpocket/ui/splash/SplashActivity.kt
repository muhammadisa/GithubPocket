package com.xoxoer.gitpocket.ui.splash

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.ui.user.UserActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadLogo()

        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, UserActivity::class.java))
        }
    }

    private fun loadLogo() {
        requestManager.load(logo).into(ImageViewLogo)
    }
}