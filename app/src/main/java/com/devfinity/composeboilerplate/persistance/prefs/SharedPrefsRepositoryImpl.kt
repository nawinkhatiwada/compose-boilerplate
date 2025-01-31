package com.devfinity.composeboilerplate.persistance.prefs

import com.devfinity.composeboilerplate.persistance.prefs.local.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SharedPrefsRepositoryImpl @Inject constructor(
    private val prefs: SharedPrefManager
) : SharedPrefsRepository {

    override fun isUserLoggedIn(): Flow<Boolean> = prefs.isUserLoggedIn()

    override fun getAccessToken(): String {
        return runBlocking {
            prefs.getAccessToken().first()
        }
    }
}