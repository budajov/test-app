package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

class Repo : BaseEntity() {
    @JsonProperty("name")
    var name: String? = null
    @JsonProperty("commits_url")
    var commitsUrl: String? = null
    @JsonProperty("open_issues_count")
    var openIssues = 0

}