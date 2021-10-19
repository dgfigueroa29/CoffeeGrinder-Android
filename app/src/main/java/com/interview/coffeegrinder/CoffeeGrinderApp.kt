package com.interview.coffeegrinder

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.io.InputStream

@HiltAndroidApp
class CoffeeGrinderApp : Application()

@GlideModule
class CoffeeGrinderGlideApp : AppGlideModule() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyAppGlideModuleEntryPoint {
        fun defaultOkHttpClient(): OkHttpClient
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val appContext = context.applicationContext
        val entryPoint: MyAppGlideModuleEntryPoint =
            EntryPointAccessors.fromApplication(appContext, MyAppGlideModuleEntryPoint::class.java)
        val client = entryPoint.defaultOkHttpClient()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }
}
