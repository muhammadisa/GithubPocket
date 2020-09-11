package com.xoxoer.gitpocket.ui.popularity

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.parcelable.Popularity
import com.xoxoer.gitpocket.models.user.Item
import com.xoxoer.gitpocket.ui.user.adapter.UsersAdapter
import com.xoxoer.gitpocket.util.common.createLoading
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_popularity.*
import javax.inject.Inject

class PopularityActivity : DaggerAppCompatActivity() {

    private lateinit var popularityViewModel: PopularityViewModel

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var loadingDialog: Dialog

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private fun setToolbar(popularity: Popularity) {
        setSupportActionBar(ToolbarPopularity)
        supportActionBar.apply {
            this?.title = popularity.popularityMode
            this?.subtitle = popularity.username
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
        }
    }

    private fun initUserAdapter() {
        usersAdapter = UsersAdapter()
        usersRecyclerView = findViewById(R.id.recyclerViewPopularity)
        usersRecyclerView.apply {
            setHasFixedSize(true)
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@PopularityActivity)
        }
    }

    private fun bindUI(gitPopularity: List<Item>) {
        if (gitPopularity.isEmpty()) Toast.makeText(
            this,
            "No ${popularityViewModel.mode.get()} to load more",
            Toast.LENGTH_SHORT
        ).show()
        else usersAdapter.setUsers(gitPopularity, popularityViewModel.append.get())
    }

    private fun initUI() {
        loadingDialog = createLoading()
        textViewLoadMorePopularity.setOnClickListener {
            val currentPage = popularityViewModel.page.get()!!
            popularityViewModel.page.set(currentPage + 1)
            popularityViewModel.append.set(true)
            determineFetchMode()
        }
    }

    private fun determineFetchMode() {
        when (popularityViewModel.mode.get()!!) {
            "Followers" -> popularityViewModel.retrieveUserFollowers()
            "Followings" -> popularityViewModel.retrieveUserFollowings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popularity)
        popularityViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(PopularityViewModel::class.java)

        // initialization
        intent.getParcelableExtra<Popularity>("POPULARITY").apply {
            popularityViewModel.userName.set(this!!.username)
            popularityViewModel.mode.set(this.popularityMode)
            setToolbar(this)
            determineFetchMode()
        }
        initUI()
        initUserAdapter()

        // loading trigger
        popularityViewModel.isLoading.observe(this, Observer { loading ->
            if (loading) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        // retrieve section
        popularityViewModel.userPopularitySuccess.observe(this, Observer { users ->
            if (users == null) return@Observer
            else bindUI(users)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}