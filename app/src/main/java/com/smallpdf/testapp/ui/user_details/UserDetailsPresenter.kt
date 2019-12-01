package com.smallpdf.testapp.ui.user_details

import com.smallpdf.testapp.api.ResponseObserver
import com.smallpdf.testapp.api.manager.RequestManager
import com.smallpdf.testapp.api.entity.ErrorDescription
import com.smallpdf.testapp.api.entity.UserDetails
import com.smallpdf.testapp.ui.base.BasePresenter
import com.smallpdf.testapp.ui.base.BaseView

class UserDetailsPresenter :
    BasePresenter<UserDetailsPresenter.View>() {

    fun userDetailsRequest() {
        if (!isNetworkAvailable()) {
            view?.handleNoInternetConnection()
            return
        }
        RequestManager.userDataRequest()
            .subscribe(object : ResponseObserver<UserDetails>() {
                override fun onSuccessfulResponse(response: UserDetails) {
                    view!!.handleUserDetails(
                        response.avatarUrl,
                        response.username,
                        response.name,
                        response.company,
                        response.reposUrl
                    )
                }

                override fun onError(responseError: ErrorDescription?) {
                    super.onError(responseError)
                    view?.handleError(responseError?.errMsg)
                }
            })
    }

    interface View : BaseView {
        fun handleUserDetails(
            userAvatarUrl: String?,
            username: String?,
            name: String?,
            company: String?,
            reposUrl: String?
        )
    }
}