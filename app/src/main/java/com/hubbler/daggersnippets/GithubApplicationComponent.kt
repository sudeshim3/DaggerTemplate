package com.hubbler.daggersnippets

import com.hubbler.daggersnippets.network.GithubService
import com.squareup.picasso.Picasso
import dagger.Component

@GithubApplicationScope
@Component(modules = [GithubServiceModule::class, PicassoModule::class])
interface GithubApplicationComponent {

    fun getPicasso():Picasso
    fun getGithubService():GithubService
}