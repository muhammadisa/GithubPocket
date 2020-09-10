package com.xoxoer.gitpocket.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.repo.GitRepo
import com.xoxoer.gitpocket.ui.repo.adapter.RepoAdapter
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_popularity.*
import kotlinx.android.synthetic.main.activity_repo.*
import javax.inject.Inject

class RepoActivity : DaggerAppCompatActivity() {

    private lateinit var repoViewModel: RepoViewModel

    private lateinit var repoRecyclerView: RecyclerView
    private lateinit var repoAdapter: RepoAdapter

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
        repoAdapter = RepoAdapter()
        repoRecyclerView = findViewById(R.id.recyclerViewRepo)
        repoRecyclerView.apply {
            setHasFixedSize(true)
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(this@RepoActivity)
        }
    }

    private fun bindUI(gitRepos: List<GitRepo>) {
        repoAdapter.setRepos(gitRepos, repoViewModel.append.get())
    }

    private fun initUI() {
        buttonLoadMoreRepo.setOnClickListener {
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
            repoViewModel.userName.set(this.toString())
            repoViewModel.retrieveUserRepository()
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