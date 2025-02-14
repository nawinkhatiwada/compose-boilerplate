package com.devfinity.composeboilerplate.persistance.prefs.local

import kotlinx.coroutines.flow.Flow

interface SharedPrefsLocal {
    fun isUserLoggedIn(): Flow<Boolean>
    fun getAccessToken(): String
}