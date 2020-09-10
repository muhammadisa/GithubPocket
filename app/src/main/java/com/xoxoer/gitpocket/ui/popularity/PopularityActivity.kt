package com.xoxoer.gitpocket.ui.popularity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.parcelable.Popularity
import com.xoxoer.gitpocket.models.user.Item
import com.xoxoer.gitpocket.ui.user.adapter.UsersAdapter
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_popularity.*
import javax.inject.Inject

class PopularityActivity : DaggerAppCompatActivity() {

    private lateinit var popularityViewModel: PopularityViewModel

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

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
        usersAdapter.setUsers(gitPopularity, false)
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
            setToolbar(this)
            when (this.popularityMode) {
                "Followers" -> popularityViewModel.retrieveUserFollowers()
                "Followings" -> popularityViewModel.retrieveUserFollowings()
            }
        }
        initUserAdapter()

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