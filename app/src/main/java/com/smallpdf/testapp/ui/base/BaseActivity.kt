package com.smallpdf.testapp.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.partial_progress_bar.*
import timber.log.Timber

abstract class BaseActivity : androidx.appcompat.app.AppCompatActivity(), ProgressBarProvider {

    private var paused = false
    private var stopped = false

    /**
     * Activities should return their layout id through this method. (ex. R.layout.this_activity_layout)
     *
     */
    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        setContentView(getLayoutId())

        rootProgressBar?.setOnClickListener {
            //do nothing... just disable touch event
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        paused = false
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
        paused = true
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
        stopped = false
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
        stopped = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d(this.javaClass.simpleName + ": onActivityResult[" + requestCode + ", " + resultCode + "]")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showProgressBar() {
        rootProgressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        rootProgressBar?.visibility = View.GONE
    }

}