package com.devfinity.composeboilerplate.persistance.prefs

import kotlinx.coroutines.flow.Flow

interface SharedPrefsRepository {
    fun isUserLoggedIn(): Flow<Boolean>
    fun getAccessToken(): String
}