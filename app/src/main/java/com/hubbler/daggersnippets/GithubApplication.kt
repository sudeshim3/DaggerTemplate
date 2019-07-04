package com.hubbler.daggersnippets

import android.app.Application
import com.hubbler.daggersnippets.network.GithubService
import com.squareup.picasso.Picasso
import timber.log.Timber

class GithubApplication: Application() {
    companion object {

    }

    lateinit var githubService:GithubService
    lateinit var picasso: Picasso

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        /*val component = DaggerGithubApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .githubServiceModule(GithubServiceModule())
            .networkModule(NetworkModule())
            .picassoModule(PicassoModule())
            .build()*/

        // Massive dependecy initialization, which is tedious, for a massive dependency tree especially for 25 or more.
        // There is an alternative, in dagger you can initialize constructor module
        //rest all is handled by dagger.

        val component = DaggerGithubApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()


        githubService = component.getGithubService()

        picasso = component.getPicasso()



    }
}