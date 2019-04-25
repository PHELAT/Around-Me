package com.workshop.aroundme.app.logger

import com.workshop.aroundme.BuildConfig

object DebugLogger {

    fun log(message: String) {
        if (BuildConfig.DEBUG) {
            println(message)
        }
    }

    fun log(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
    }

}
