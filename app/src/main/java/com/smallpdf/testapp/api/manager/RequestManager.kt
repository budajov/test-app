package com.smallpdf.testapp.api.manager

import com.smallpdf.testapp.api.entity.Commit
import com.smallpdf.testapp.api.entity.Repo
import com.smallpdf.testapp.api.entity.UserDetails
import com.smallpdf.testapp.api.rest.RestClient
import io.reactivex.Single
import retrofit2.Response

object RequestManager {

    fun userDataRequest(): Single<Response<UserDetails>> {
        val username = "octocat"
        return RestClient.INSTANCE.getApiService()!!.getUserData(username)
    }

    fun getUserReposRequest(reposUrl: String?): Single<Response<List<Repo>>> {
        return RestClient.INSTANCE.getApiService()!!.getUserRepos(reposUrl)
    }

    fun getCommitDetails(commitsUrl: String?): Single<Response<List<Commit>>> {
        val url = commitsUrl?.replace("{/sha}", "")
        return RestClient.INSTANCE.getApiService()!!.getCommitDetails(url)
    }
}