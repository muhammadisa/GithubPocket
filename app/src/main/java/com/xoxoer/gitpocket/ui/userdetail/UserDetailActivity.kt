package com.xoxoer.gitpocket.ui.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xoxoer.gitpocket.R
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private fun setToolbar() {
        setSupportActionBar(ToolbarUserDetail)
        supportActionBar.apply {
            title = "Detail user"
            this?.subtitle = "8 followings & 12 followers"
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // initialization
        setToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}