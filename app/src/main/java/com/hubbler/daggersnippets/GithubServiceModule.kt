package com.hubbler.daggersnippets

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hubbler.daggersnippets.network.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class GithubServiceModule {

    @Provides
    @GithubApplicationScope
    fun getRetrofit(gson:Gson, okHttpClient:OkHttpClient):Retrofit {
       return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl("http://api.github.com/")
            .build()
    }

    @Provides
    @GithubApplicationScope
    fun getGithubService(retrofit: Retrofit):GithubService {
          return retrofit.create(GithubService::class.java)
     }

    @Provides
    @GithubApplicationScope
    fun gson():Gson {
        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(DateTime)
        return  gsonBuilder.create()
    }
}