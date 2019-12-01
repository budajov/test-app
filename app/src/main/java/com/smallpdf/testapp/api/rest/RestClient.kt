package com.smallpdf.testapp.api.rest

import com.smallpdf.testapp.api.hub.ApiService
import com.smallpdf.testapp.api.rest.ServiceGenerator.createService

enum class RestClient {
    INSTANCE;

    private var apiService: ApiService? = null
    var token: String? = null
    /**
     * Clear all rest services and authorization token.
     */
    fun reset() {
        token = null
        apiService = null
    }

    fun getApiService(): ApiService? {
        if (apiService == null) {
            apiService = createService(
                ApiService::class.java
            )
        }
        return apiService
    }
}