package com.smallpdf.testapp.ui.commits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallpdf.testapp.R
import com.smallpdf.testapp.api.entity.Commit
import com.smallpdf.testapp.helper.BundleHelper
import com.smallpdf.testapp.ui.adapter.CommitsAdapter
import com.smallpdf.testapp.ui.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_commits.*
import java.util.*

class CommitsFragment : BaseMvpFragment<CommitsPresenter.View, CommitsPresenter>(),
    CommitsPresenter.View {
    private var commits: MutableList<Commit>? = null
    private var commitsAdapter: CommitsAdapter? = null

    private var rvCommits: RecyclerView? = null

    override fun createPresenter(): CommitsPresenter {
        return CommitsPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_commits
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setMenuTitle(getString(R.string.commits))
        showBackButton(true)
        val commitsUrl = arguments!!.getString(BundleHelper.COMMITS_URL_KEY)
        showProgressBar()
        presenter!!.getCommits(commitsUrl)
        commits = ArrayList()
        commitsAdapter = CommitsAdapter(activity!!, commits)

        rvCommits = rootView?.findViewById(R.id.rvCommits)
        rvCommits?.layoutManager = LinearLayoutManager(activity)
        rvCommits?.adapter = commitsAdapter
        return rootView
    }

    override fun handleCommits(commits: List<Commit>) {
        hideProgressBar()
        this.commits!!.addAll(commits)
        commitsAdapter!!.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(commitsUrl: String?): CommitsFragment {
            val args = Bundle()
            args.putString(BundleHelper.COMMITS_URL_KEY, commitsUrl)
            val fragment = CommitsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}