package com.hubbler.daggersnippets.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("repositories")
    fun getAllRepos(): Call<List<String>>

    @GET("users/{username}")
    fun getUser(@Path("username") userName:String)
}