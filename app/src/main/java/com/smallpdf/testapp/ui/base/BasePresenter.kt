package com.smallpdf.testapp.ui.base

import androidx.annotation.CallSuper
import com.smallpdf.testapp.helper.Util

abstract class BasePresenter<T : BaseView>() {

    protected var view: T? = null

    @CallSuper
    open fun attachView(view: T) {
        this.view = view
    }

    @CallSuper
    open fun detachView() {
        this.view = null
    }

    protected fun isViewAttached() = view != null

    protected fun isNetworkAvailable(): Boolean {
        return Util.instance!!.hasConnection()
    }

}
