package com.tiriig.whatsdeleted

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatsDeleted: Application() {
    override fun onCreate() {
        super.onCreate()
   }
}