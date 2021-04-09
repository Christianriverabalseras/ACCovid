package com.ac03.covid

import android.app.Application
import com.ac03.covid.data.database.CovidDatabase
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CovidApplication : Application() {

    val flipperNetworkPlugin by lazy { NetworkFlipperPlugin() }

    companion object {
        private lateinit var INSTANCE: CovidApplication
        fun get(): CovidApplication = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initializeFlipper()
    }

    private fun initializeFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(applicationContext).apply {
                addPlugin(
                    InspectorFlipperPlugin(
                        applicationContext,
                        DescriptorMapping.withDefaults()
                    )
                )
                addPlugin(flipperNetworkPlugin)
            }.start()
        }
    }
}
