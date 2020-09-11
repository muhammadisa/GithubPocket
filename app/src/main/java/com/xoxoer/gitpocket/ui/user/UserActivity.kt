package com.xoxoer.gitpocket.ui.user

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.user.GitUsers
import com.xoxoer.gitpocket.ui.user.adapter.UsersAdapter
import com.xoxoer.gitpocket.util.common.*
import com.xoxoer.gitpocket.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject


class UserActivity : DaggerAppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var loadingDialog: Dialog

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private fun setToolbar() {
        setSupportActionBar(ToolbarSearchUser)
        supportActionBar?.setLogo(R.drawable.ic_github_logo_text)
    }

    private fun initUserAdapter() {
        usersAdapter = UsersAdapter()
        usersRecyclerView = findViewById(R.id.recyclerViewUsers)
        usersRecyclerView.apply {
            setHasFixedSize(true)
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@UserActivity)
        }
    }

    private fun bindUI(gitUsers: GitUsers) {
        textViewTotal.text = gitUsers.total_count.toString()
        textViewQuery.text = userViewModel.searchQuery.get().toString()
        textViewCurrentPage.text = userViewModel.page.get().toString()
        textViewLoadMoreUser.show()
        if (gitUsers.items.isEmpty()) Toast.makeText(
            this,
            "No user to load more",
            Toast.LENGTH_SHORT
        ).show()
        else usersAdapter.setUsers(gitUsers.items, userViewModel.append.get())
    }

    private fun initUI() {
        loadingDialog = createLoading()
        editTextSearchUser.onTextChange { query ->
            userViewModel.searchQuery.set(query)
        }
        editTextSearchUser.onSearchPressed {
            userViewModel.retrieveUsers()
            this.dismissKeyboard()
            editTextSearchUser.clearFocus()
        }
        editTextSearchUser.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                textViewLoadMoreUser.gone()
                userViewModel.page.set(1)
                usersAdapter.clearUsers()
            }
        }
        textViewLoadMoreUser.setOnClickListener {
            val currentPage = userViewModel.page.get()!!
            userViewModel.page.set(currentPage + 1)
            userViewModel.append.set(true)
            userViewModel.retrieveUsers()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(UserViewModel::class.java)

        // initialization
        initUI()
        setToolbar()
        initUserAdapter()

        // loading trigger
        userViewModel.isLoading.observe(this, Observer { loading ->
            if (loading) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        // retrieve section
        userViewModel.usersSuccess.observe(this, Observer { users ->
            if (users == null) return@Observer
            else bindUI(users)
        })
    }
}