package com.codep.rossin

import android.app.Application
//import android.net.http.HttpResponseCache.install
import com.codep.data.dl.dataModule
import com.codep.domain.dl.domainModule
import com.codep.rossin.dl.presentationModule
import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
//import java.util.logging.LoggingPermission

class MossinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MossinApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))
        }

    }
}