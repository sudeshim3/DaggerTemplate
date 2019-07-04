package com.hubbler.daggersnippets

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @GithubApplicationScope
    fun loggingInterceptor():HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object:HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }

        })
    }

    @Provides
    @GithubApplicationScope
    fun cache(cacheFile: File):Cache {
        return Cache(cacheFile, 10 * 1024 * 1024) //10 mb cache
    }

    @Provides
    @GithubApplicationScope
    fun cacheFile(context:Context):File {
      return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @GithubApplicationScope
    fun getOkhttpClient(loggingInterceptor: HttpLoggingInterceptor, cache:Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }
}