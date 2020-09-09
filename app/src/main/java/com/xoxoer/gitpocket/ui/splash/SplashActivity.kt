package com.xoxoer.gitpocket.ui.splash

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.xoxoer.gitpocket.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
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
    }

    private fun loadLogo() {
        requestManager.load(logo).into(ImageViewLogo)
    }
}