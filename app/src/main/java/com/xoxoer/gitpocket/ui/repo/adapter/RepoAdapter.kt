package com.xoxoer.gitpocket.ui.repo.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.gitpocket.R
import com.xoxoer.gitpocket.models.repo.GitRepo
import kotlinx.android.synthetic.main.card_view_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ReposViewHolder>() {

    private val repos: MutableList<GitRepo> = mutableListOf()

    internal fun setRepos(repos: List<GitRepo>, append: Boolean) {
        if (append) {
            this.repos.addAll(repos)
        } else {
            this.repos.clear()
            this.repos.addAll(repos)
        }
        notifyDataSetChanged()
    }

    inner class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoTitle: TextView = itemView.textViewTitle
        val repoDescription: TextView = itemView.textViewDescription
        val repoLanguage: TextView = itemView.textViewLanguage
        val repoLicence: TextView = itemView.textViewLicense
        val repoWatcher: TextView = itemView.textViewWatcher
        val repoForks: TextView = itemView.textViewForks
        val repoVisitRepo: TextView = itemView.textViewVisitRepo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_repo, parent, false)
        return ReposViewHolder(itemView)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        with(holder) {
            val currentRepo = repos[position]
            repoTitle.text = currentRepo.name
            repoDescription.text = currentRepo.description
            repoLanguage.text = currentRepo.language
            repoLicence.text = if (currentRepo.license != null) {
                currentRepo.license.name
            } else {
                "No License"
            }
            repoWatcher.text = currentRepo.watchers.toString()
            repoForks.text = currentRepo.forks.toString()
            repoVisitRepo.setOnClickListener {
                it.context.startActivity(
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(currentRepo.cloneUrl))
                )
            }
        }
    }

}