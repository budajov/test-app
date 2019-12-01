package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

class ErrorDescription : BaseEntity {
    @JsonProperty("message")
    var errMsg: String? = null
    @JsonIgnore
    var isNetworkError = false

    constructor()
    constructor(errMsg: String?) {
        this.errMsg = errMsg
    }

}