package com.smallpdf.testapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.smallpdf.testapp.R
import org.w3c.dom.Text
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    private var ivBack: ImageView? = null
    private var tvMenuTitle: TextView? = null

    protected var rootView: View? = null
    private var paused = false
    private var stopped = false

    private var progressBarProvider: ProgressBarProvider? = null


    /**
     * Fragments should return their layout id through this method. (ex. R.layout.this_fragment_layout)
     */
    protected abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("onAttach")
        progressBarProvider = context as ProgressBarProvider
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(getLayoutId(), null)

        ivBack = rootView?.findViewById(R.id.ivBack)
        tvMenuTitle = rootView?.findViewById(R.id.tvMenuTitle)

        ivBack?.setOnClickListener {
            activity!!.finish()
        }

        // Prevents clicks from "going through fragments" if they are added one on top of another
        rootView?.setOnTouchListener { v, event -> true }

        return rootView
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

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        paused = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
        stopped = true
    }

    protected fun setMenuTitle(title: String) {
        tvMenuTitle!!.text = title
    }

    protected fun showBackButton(visible: Boolean) {
        ivBack!!.visibility = if (visible) View.VISIBLE else View.GONE
    }


    protected fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgressBar() {
        progressBarProvider?.showProgressBar()
    }

    protected fun hideProgressBar() {
        progressBarProvider?.hideProgressBar()
    }
}