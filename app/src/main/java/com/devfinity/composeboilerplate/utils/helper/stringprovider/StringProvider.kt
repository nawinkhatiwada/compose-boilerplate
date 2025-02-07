package com.devfinity.composeboilerplate.utils.helper.stringprovider

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}