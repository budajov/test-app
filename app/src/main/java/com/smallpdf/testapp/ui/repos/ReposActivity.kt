package com.smallpdf.testapp.ui.repos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smallpdf.testapp.R
import com.smallpdf.testapp.helper.BundleHelper
import com.smallpdf.testapp.ui.base.BaseActivity

class ReposActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_repos
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val reposUrl = intent?.extras?.getString(BundleHelper.REPOS_URL_KEY)
            ?: throw NullPointerException("reposUrl must be non null value")
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    ReposFragment.newInstance(reposUrl),
                    ReposFragment::class.java.simpleName
                )
                .commit()
        }
    }

    companion object {
        fun startActivity(context: Context?, reposUrl: String?) {
            val intent = Intent(context, ReposActivity::class.java)
            intent.putExtra(BundleHelper.REPOS_URL_KEY, reposUrl)
            context?.startActivity(intent)
        }
    }
}