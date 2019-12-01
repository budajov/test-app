package com.smallpdf.testapp.ui.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smallpdf.testapp.R
import com.smallpdf.testapp.helper.ImageLoader
import com.smallpdf.testapp.ui.base.BaseMvpFragment
import com.smallpdf.testapp.ui.repos.ReposActivity
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : BaseMvpFragment<UserDetailsPresenter.View, UserDetailsPresenter>(),
    UserDetailsPresenter.View {

    companion object {
        fun newInstance(): UserDetailsFragment {
            val args = Bundle()
            val fragment = UserDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun createPresenter(): UserDetailsPresenter {
        return UserDetailsPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user_details
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        setMenuTitle(getString(R.string.user_details))
        showBackButton(false)

        showProgressBar()
        presenter?.userDetailsRequest()

        return rootView
    }

    override fun handleUserDetails(
        userAvatarUrl: String?,
        username: String?,
        name: String?,
        company: String?,
        reposUrl: String?
    ) {
        hideProgressBar()
        ImageLoader.instance?.loadImage(userAvatarUrl, ivUserAvatar)
        tvUsername?.text = "@$username"
        tvName?.text = name
        tvCompany?.text = "${getString(R.string.company)}: $company"
        if (reposUrl == null) {
            btnRepos?.visibility = View.GONE
        }
        btnRepos?.setOnClickListener {
            ReposActivity.startActivity(activity, reposUrl)
        }
    }


}
