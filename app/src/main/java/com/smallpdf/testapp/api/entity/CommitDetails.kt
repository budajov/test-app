package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

class CommitDetails : BaseEntity() {
    @JsonProperty("committer")
    var committer: Committer? = null
    @JsonProperty("message")
    var message: String? = null
    @JsonProperty("comment_count")
    var commentsCount = 0

}