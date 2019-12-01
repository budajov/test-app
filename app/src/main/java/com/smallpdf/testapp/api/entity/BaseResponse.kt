package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

abstract class BaseResponse : BaseEntity() {
    @JsonProperty("message")
    var errMsg: String? = null
}