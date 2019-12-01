package com.smallpdf.testapp.ui.user_details

import android.os.Bundle
import com.smallpdf.testapp.R
import com.smallpdf.testapp.ui.base.BaseActivity
import com.smallpdf.testapp.ui.user_details.UserDetailsFragment
import com.smallpdf.testapp.ui.user_details.UserDetailsFragment.Companion.newInstance

class UserDetailsActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_user_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    newInstance(),
                    UserDetailsFragment::class.java.simpleName
                )
                .commit()
        }
    }
}