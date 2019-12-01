package com.smallpdf.testapp.ui.commits

import com.smallpdf.testapp.api.ResponseObserver
import com.smallpdf.testapp.api.entity.Commit
import com.smallpdf.testapp.api.entity.ErrorDescription
import com.smallpdf.testapp.api.manager.RequestManager.getCommitDetails
import com.smallpdf.testapp.ui.base.BasePresenter
import com.smallpdf.testapp.ui.base.BaseView

class CommitsPresenter : BasePresenter<CommitsPresenter.View>() {
    fun getCommits(commitsUrl: String?) {
        if (!isNetworkAvailable()) {
            view!!.handleNoInternetConnection()
            return
        }
        getCommitDetails(commitsUrl)
            .subscribe(object : ResponseObserver<List<Commit>>() {
                override fun onSuccessfulResponse(response: List<Commit>) {
                    view!!.handleCommits(response)
                }

                override fun onError(responseError: ErrorDescription?) {
                    super.onError(responseError)
                    view!!.handleError(responseError!!.errMsg)
                }
            })
    }

    interface View : BaseView {
        fun handleCommits(commits: List<Commit>)
    }
}