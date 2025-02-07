package com.devfinity.composeboilerplate.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment

fun Context.log(message: String?, logLevel: LogLevel = LogLevel.DEBUG) {
    val appName = this.getString(this.applicationInfo.labelRes)
    printLog("${appName}: =====>", message, logLevel)
}

fun Activity.log(message: String?, logLevel: LogLevel = LogLevel.DEBUG) {
    val appName = this.getString(this.applicationInfo.labelRes)
    printLog("${appName}: =====>", message, logLevel)
}

fun Fragment.log(message: String?, logLevel: LogLevel = LogLevel.DEBUG) {
    val appName = this.getString(this.requireActivity().applicationInfo.labelRes)
    printLog("${appName}: =====>", message, logLevel)
}

fun log(message: String?, logLevel: LogLevel = LogLevel.DEBUG) {
    printLog(message = message, logLevel = logLevel)
}

private fun printLog(
    tag: String = "App Log: =====>",
    message: String?,
    logLevel: LogLevel = LogLevel.DEBUG
) {
    when (logLevel) {
        LogLevel.DEBUG -> Log.d(tag, message.toString())
        LogLevel.VERBOSE -> Log.v(tag, message.toString())
        LogLevel.INFO -> Log.i(tag, message.toString())
        LogLevel.ERROR -> Log.e(tag, message.toString())
        LogLevel.WARN -> Log.w(tag, message.toString())
    }
}

enum class LogLevel {
    DEBUG, VERBOSE, INFO, ERROR, WARN
}