package com.xoxoer.gitpocket.ui.userdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.parcelable.Popularity
import com.xoxoer.gitpocket.models.userdetail.GitUserDetail
import com.xoxoer.gitpocket.ui.popularity.PopularityActivity
import com.xoxoer.gitpocket.ui.repo.RepoActivity
import com.xoxoer.gitpocket.util.common.circle
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

class UserDetailActivity : DaggerAppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private fun setToolbar() {
        setSupportActionBar(ToolbarUserDetail)
        supportActionBar.apply {
            title = "Detail user"
            this?.subtitle = "waiting for data"
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindUI(gitUserDetail: GitUserDetail) {
        userDetailViewModel.userName.set(gitUserDetail.login)
        textViewRealName.text = gitUserDetail.name
        textViewUsername.text = gitUserDetail.login
        textViewBioData.text = gitUserDetail.bio
        textViewLocationName.text = gitUserDetail.location
        textViewCompanyName.text = gitUserDetail.company
        textViewLinks.text = gitUserDetail.blog
        textViewEmail.text = gitUserDetail.email
        textViewTweeterName.text = "@${gitUserDetail.twitterUsername}"
        textViewPublicRepo.text = "Public Repositories ${gitUserDetail.publicRepos}"
        imageViewAvatar.circle(gitUserDetail.avatarUrl)
        supportActionBar?.subtitle =
            "${gitUserDetail.following} followings & ${gitUserDetail.followers} followers"
    }

    private fun initUI() {
        buttonShowFollowers.setOnClickListener {
            startActivity(
                Intent(this@UserDetailActivity, PopularityActivity::class.java)
                    .putExtra(
                        "POPULARITY", Popularity(
                            "Followers",
                            userDetailViewModel.userName.get()!!
                        )
                    )
            )
        }
        buttonShowFollowing.setOnClickListener {
            startActivity(
                Intent(this@UserDetailActivity, PopularityActivity::class.java)
                    .putExtra(
                        "POPULARITY", Popularity(
                            "Followings",
                            userDetailViewModel.userName.get()!!
                        )
                    )
            )
        }
        buttonShowRepository.setOnClickListener {
            startActivity(
                Intent(this@UserDetailActivity, RepoActivity::class.java)
                    .putExtra("USERNAME", userDetailViewModel.userName.get()!!)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        userDetailViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(UserDetailViewModel::class.java)

        // initialization
        initUI()
        setToolbar()
        intent.extras?.get("USERNAME").run {
            userDetailViewModel.userName.set(this.toString())
            userDetailViewModel.retrieveUserDetail()
        }

        // retrieve section
        userDetailViewModel.userDetailSuccess.observe(this, Observer { userDetail ->
            if (userDetail == null) return@Observer
            else bindUI(userDetail)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}