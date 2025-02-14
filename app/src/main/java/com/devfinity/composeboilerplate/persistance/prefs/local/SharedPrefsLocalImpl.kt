package com.devfinity.composeboilerplate.persistance.prefs.local

import com.devfinity.composeboilerplate.persistance.prefs.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SharedPrefsLocalImpl @Inject constructor(
    private val prefs: SharedPrefManager
) : SharedPrefsLocal {

    override fun isUserLoggedIn(): Flow<Boolean> = prefs.isUserLoggedIn()

    override fun getAccessToken(): String {
        return runBlocking {
            prefs.getAccessToken().first()
        }
    }
}