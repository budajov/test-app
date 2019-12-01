package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity
import java.util.*

class Committer : BaseEntity() {
    @JsonProperty("name")
    var name: String? = null
    @JsonProperty("date")
    var date: Date? = null

}