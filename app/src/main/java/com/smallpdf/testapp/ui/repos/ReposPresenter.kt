package com.smallpdf.testapp.ui.repos

import com.smallpdf.testapp.api.ResponseObserver
import com.smallpdf.testapp.api.entity.ErrorDescription
import com.smallpdf.testapp.api.entity.Repo
import com.smallpdf.testapp.api.manager.RequestManager
import com.smallpdf.testapp.ui.base.BasePresenter
import com.smallpdf.testapp.ui.base.BaseView

class ReposPresenter : BasePresenter<ReposPresenter.View>() {

    fun getUserReposRequest(reposUrl: String?) {
        if (!isNetworkAvailable()) {
            view!!.handleNoInternetConnection()
            return
        }
        RequestManager.getUserReposRequest(reposUrl)
            ?.subscribe(object : ResponseObserver<List<Repo>>() {
                override fun onSuccessfulResponse(response: List<Repo>) {
                    view!!.handleUserRepos(response)
                }

                override fun onError(responseError: ErrorDescription?) {
                    super.onError(responseError)
                    view!!.handleError(responseError?.errMsg)
                }
            })
    }

    interface View : BaseView {
        fun handleUserRepos(response: List<Repo>)
    }
}