package com.xoxoer.gitpocket.ui.user.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.user.Item
import com.xoxoer.gitpocket.ui.userdetail.UserDetailActivity
import com.xoxoer.gitpocket.util.common.circle
import kotlinx.android.synthetic.main.card_view_user.view.*

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val tag = "UserAdapter"

    private val users: MutableList<Item> = mutableListOf()

    internal fun setUsers(users: List<Item>, append: Boolean) {
        if (append) {
            this.users.addAll(users)
        } else {
            this.users.clear()
            this.users.addAll(users)
        }
        notifyDataSetChanged()
    }

    internal fun clearUsers() {
        this.users.clear()
        notifyItemRangeChanged(0, this.users.size)
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userCardUser: CardView = itemView.cardViewUser
        val userAvatar: ImageView = itemView.imageViewAvatar
        val userCardViewName: TextView = itemView.textViewName
        val userCardViewType: TextView = itemView.textViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_user, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        with(holder) {
            val currentUser = users[position]
            userAvatar.circle(currentUser.avatar_url)
            userCardViewName.text = currentUser.login
            userCardViewType.text = currentUser.type
            userCardUser.setOnClickListener {
                Log.e(tag, "current user ${currentUser.login}")
                it.context.startActivity(
                    Intent(it.context, UserDetailActivity::class.java)
                        .putExtra("USERNAME", currentUser.login)
                )
            }
        }
    }

}