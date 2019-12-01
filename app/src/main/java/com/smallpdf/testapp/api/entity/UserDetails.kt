package com.smallpdf.testapp.api.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.smallpdf.testapp.api.BaseEntity

class UserDetails : BaseEntity() {
    @JsonProperty("login")
    var username: String? = null
    @JsonProperty("avatar_url")
    var avatarUrl: String? = null
    @JsonProperty("repos_url")
    var reposUrl: String? = null
    @JsonProperty("name")
    var name: String? = null
    @JsonProperty("company")
    var company: String? = null

}