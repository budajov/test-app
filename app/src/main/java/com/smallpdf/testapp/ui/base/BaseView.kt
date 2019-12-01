package com.smallpdf.testapp.ui.base

interface BaseView {

    fun handleError(errMsg: String?)

    fun handleNoInternetConnection()
}