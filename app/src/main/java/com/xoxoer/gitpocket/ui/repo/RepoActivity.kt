package com.xoxoer.gitpocket.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.repo.GitRepo
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_popularity.*
import kotlinx.android.synthetic.main.activity_repo.*
import javax.inject.Inject

class RepoActivity : AppCompatActivity() {

    private lateinit var repoViewModel: RepoViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private fun setToolbar(userName: String) {
        setSupportActionBar(ToolbarRepo)
        supportActionBar.apply {
            this?.title = "Repository of"
            this?.subtitle = userName
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
        }
    }

    private fun initRepoAdapter() {

    }

    private fun bindUI(gitRepos: List<GitRepo>) {

    }

    private fun initUI() {
        buttonLoadMorePopularity.setOnClickListener {
            val currentPage = repoViewModel.page.get()!!
            repoViewModel.page.set(currentPage + 1)
            repoViewModel.append.set(true)
            repoViewModel.retrieveUserRepository()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        repoViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(RepoViewModel::class.java)

        // initialization
        intent.extras?.getString("USERNAME").run {
            setToolbar(this.toString())
        }
        initUI()
        initRepoAdapter()

        // retrieve section
        repoViewModel.userRepositorySuccess.observe(this, Observer { repositories ->
            if (repositories == null) return@Observer
            else bindUI(repositories)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}