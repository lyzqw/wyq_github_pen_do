package com.wyq_github_pen_do

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.tencent.bugly.crashreport.CrashReport
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()

        CrashReport.initCrashReport(applicationContext, "607527734c", false);

        startKoin {
            androidContext(this@ApplicationController)
            modules(AppModule.loadFeature)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        Utils.init(this)
    }
}