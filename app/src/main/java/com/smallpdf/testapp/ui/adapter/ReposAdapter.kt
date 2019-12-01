package com.smallpdf.testapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smallpdf.testapp.R
import com.smallpdf.testapp.api.entity.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class ReposAdapter(
    private val context: Context,
    private val repos: MutableList<Repo>?,
    private val onClickRepoItemListener: OnClickRepoItemListener
) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val repo = repos?.get(position)
        holder.setParentData(repo, position)
    }

    override fun getItemCount(): Int {
        return repos!!.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun setParentData(repo: Repo?, position: Int) {
            itemView.tvRepoName?.text = repo?.name
            itemView.tvOpenIssues?.text = "Open issues: ${repo?.openIssues}"
            itemView.setOnClickListener {
                onClickRepoItemListener?.onClick(repo?.commitsUrl)
            }
        }
    }


    public interface OnClickRepoItemListener {

        fun onClick(commitsUrl: String?)
    }

}