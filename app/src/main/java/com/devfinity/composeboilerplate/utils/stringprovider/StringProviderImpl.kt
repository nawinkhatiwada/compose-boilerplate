package com.devfinity.composeboilerplate.utils.stringprovider

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringProviderImpl @Inject constructor(private val context: Context) : StringProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}