package com.smallpdf.testapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smallpdf.testapp.R
import com.smallpdf.testapp.api.entity.Commit
import com.smallpdf.testapp.helper.DateTimeHelper
import kotlinx.android.synthetic.main.item_commit.view.*

class CommitsAdapter(
    private val context: Context,
    private val commits: MutableList<Commit>?
) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_commit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val commit = commits!![position]
        holder.setParentData(commit, position)
    }

    override fun getItemCount(): Int {
        return commits!!.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun setParentData(commit: Commit, position: Int) {
            itemView.tvCommitSha?.text = commit.sha
            itemView.tvCommitter?.text = commit.commitDetails?.committer?.name
            itemView.tvCommitDate?.text = DateTimeHelper.convertDateToStringUTC(
                commit.commitDetails?.committer?.date,
                DateTimeHelper.SIMPLE_DATE_FORMAT
            )
            itemView.tvCommitMessage?.text = commit.commitDetails?.message
            itemView.tvComments?.text = String.format(
                context.getString(R.string.comments),
                commit.commitDetails?.commentsCount
            )
            itemView.setOnClickListener {
                commit.isExpanded = !commit.isExpanded
                checkDetails(commit)
            }

            checkDetails(commit)
        }

        private fun checkDetails(commit: Commit?) {
            if (!commit!!.isExpanded) {
                itemView.llDetails.visibility = View.GONE
            } else {
                itemView.llDetails.visibility = View.VISIBLE
            }
        }
    }

}