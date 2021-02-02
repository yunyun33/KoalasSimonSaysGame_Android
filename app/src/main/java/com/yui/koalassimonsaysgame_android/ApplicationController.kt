package com.yui.koalassimonsaysgame_android

import android.app.Application
import android.content.Context

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationController? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}