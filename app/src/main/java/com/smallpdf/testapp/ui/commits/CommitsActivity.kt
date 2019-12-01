package com.smallpdf.testapp.ui.commits

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smallpdf.testapp.R
import com.smallpdf.testapp.helper.BundleHelper
import com.smallpdf.testapp.ui.base.BaseActivity

class CommitsActivity : BaseActivity() {

    companion object {
        fun startActivity(
            context: Context?,
            commitsUrl: String?
        ) {
            val intent = Intent(context, CommitsActivity::class.java)
            intent.putExtra(BundleHelper.COMMITS_URL_KEY, commitsUrl)
            context?.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_commits
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val commitsUrl = intent?.extras?.getString(BundleHelper.COMMITS_URL_KEY)
            ?: throw NullPointerException("commitsUrl must be non null value")

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    CommitsFragment.newInstance(commitsUrl),
                    CommitsFragment::class.java.simpleName
                )
                .commit()
        }
    }
}