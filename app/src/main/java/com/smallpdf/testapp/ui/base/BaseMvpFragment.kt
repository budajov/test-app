package com.smallpdf.testapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smallpdf.testapp.R

/**
 * Base fragment for this simple mvp architecture. Presenters are created and attached in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} and are detached and forgotten about in {@link #onDestroyView()}.</p>
 */
abstract class BaseMvpFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment(),
    BaseView {

    protected var presenter: P? = null

    /**
     * Fragments should create their presenter in this method.
     * It is invoked in {@link #onCreate(Bundle)} and the presenter is attached to its view in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     */
    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        presenter?.attachView(this as V)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
        presenter = null
    }

    override fun handleError(errMsg: String?) {
        hideProgressBar()
        showToast(getString(R.string.something_went_wrong))
    }

    override fun handleNoInternetConnection() {
        hideProgressBar()
        showToast(getString(R.string.check_internet_connection))
    }
}