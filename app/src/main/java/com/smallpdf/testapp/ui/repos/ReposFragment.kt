package com.smallpdf.testapp.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smallpdf.testapp.R
import com.smallpdf.testapp.api.entity.Repo
import com.smallpdf.testapp.helper.BundleHelper
import com.smallpdf.testapp.ui.adapter.ReposAdapter
import com.smallpdf.testapp.ui.adapter.ReposAdapter.OnClickRepoItemListener
import com.smallpdf.testapp.ui.base.BaseMvpFragment
import com.smallpdf.testapp.ui.commits.CommitsActivity
import kotlinx.android.synthetic.main.fragment_repos.*
import java.util.*

class ReposFragment : BaseMvpFragment<ReposPresenter.View, ReposPresenter>(), ReposPresenter.View {

    companion object {
        fun newInstance(reposUrl: String?): ReposFragment {
            val args = Bundle()
            args.putString(BundleHelper.REPOS_URL_KEY, reposUrl)
            val fragment = ReposFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var reposAdapter: ReposAdapter? = null
    private var repos: MutableList<Repo>? = null

    private var rvRepos: RecyclerView? = null

    override fun createPresenter(): ReposPresenter {
        return ReposPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_repos
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setMenuTitle(getString(R.string.user_repos))
        showBackButton(true)
        val reposUrl = arguments!!.getString(BundleHelper.REPOS_URL_KEY)
        showProgressBar()
        presenter!!.getUserReposRequest(reposUrl)
        repos = ArrayList()
        reposAdapter = ReposAdapter(activity!!, repos, object : OnClickRepoItemListener {
            override fun onClick(commitsUrl: String?) {
                CommitsActivity.startActivity(activity, commitsUrl)
            }
        })
        rvRepos = rootView?.findViewById(R.id.rvRepos)
        rvRepos?.layoutManager = LinearLayoutManager(activity)
        rvRepos?.adapter = reposAdapter
        return rootView
    }


    override fun handleUserRepos(response: List<Repo>) {
        hideProgressBar()
        repos?.addAll(response)
        reposAdapter?.notifyDataSetChanged()
    }
}