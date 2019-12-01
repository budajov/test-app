package com.smallpdf.testapp.api.hub

import com.smallpdf.testapp.api.entity.Commit
import com.smallpdf.testapp.api.entity.Repo
import com.smallpdf.testapp.api.entity.UserDetails
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("users/{username}")
    fun getUserData(@Path("username") username: String?): Single<Response<UserDetails>>

    @GET
    fun getUserRepos(@Url reposUrl: String?): Single<Response<List<Repo>>>

    @GET
    fun getCommitDetails(@Url commitDetailsUrl: String?): Single<Response<List<Commit>>>
}