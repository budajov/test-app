package com.smallpdf.testapp.api

import com.smallpdf.testapp.AppContext
import com.smallpdf.testapp.api.entity.ErrorDescription
import com.smallpdf.testapp.api.entity.BaseResponse
import com.smallpdf.testapp.api.entity.ErrorResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

abstract class ResponseObserver<T> : SingleObserver<Response<T>> {
    /**
     * Parses the api error body message in its standardised format if its present
     *
     * @param response
     * @return
     */
    private fun getApiErrorResponseStatus(response: Response<*>?): ErrorDescription {
        val responseError =
            ErrorDescription(DEFAULT_ERROR_DESCRIPTION)
        if (response != null && !response.isSuccessful) {
            try {
                val parsedErrorResponse: BaseResponse? =
                    AppContext.instance?.jacksonObjectMapper
                        ?.readValue(
                            response.errorBody()!!.string(),
                            ErrorResponse::class.java
                        )
                if (parsedErrorResponse?.errMsg != null) {
                    responseError.errMsg = parsedErrorResponse.errMsg
                }
            } catch (e: IOException) {
                Timber.e(e, "Error parsing server error response")
            }
        }
        return responseError
    }

    override fun onSubscribe(d: Disposable) { //do nothing
    }

    override fun onSuccess(response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            body?.let { onSuccessfulResponse(it) }
        } else {
            onError(getApiErrorResponseStatus(response))
        }
    }

    override fun onError(e: Throwable) {
        if (e != null) {
            Timber.e(e.message)
        }
        val errorResponse: ErrorDescription
        errorResponse = if (e is HttpException) {
            getApiErrorResponseStatus(e.response())
        } else {
            ErrorDescription(DEFAULT_ERROR_DESCRIPTION)
        }
        if (e is IOException) {
            errorResponse.isNetworkError = true
        }
        onError(errorResponse)
    }

    /**
     * Invoked on a successful response from server. (keep in mind that dataResponse might be null for some api calls even though its successful)
     *
     * @param response
     */
    abstract fun onSuccessfulResponse(response: T)

    open fun onError(responseError: ErrorDescription?) {}

    companion object {
        private const val DEFAULT_ERROR_DESCRIPTION = "Something went wrong"
    }
}