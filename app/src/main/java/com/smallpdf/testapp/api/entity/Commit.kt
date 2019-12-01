package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

class Commit : BaseEntity() {
    @JsonProperty("sha")
    var sha: String? = null
    @JsonProperty("commit")
    var commitDetails: CommitDetails? = null
    @JsonIgnore
    var isExpanded = false

}