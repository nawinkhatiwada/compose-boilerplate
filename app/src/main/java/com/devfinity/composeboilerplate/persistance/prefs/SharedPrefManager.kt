package com.devfinity.composeboilerplate.persistance.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.devfinity.composeboilerplate.persistance.prefs.PrefsKeys.ACCESS_TOKEN
import com.devfinity.composeboilerplate.persistance.prefs.PrefsKeys.IS_USER_LOGGED_IN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(
    private val context: Context
) {
    private val Context.prefsDataStore: DataStore<Preferences> by preferencesDataStore(name = PrefsKeys.PREF_NAME)

    fun isUserLoggedIn(): Flow<Boolean> =
        context.prefsDataStore.data.map {
            it[IS_USER_LOGGED_IN] ?: true
        }

    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        context.prefsDataStore.edit {
            it[IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }

    fun getAccessToken(): Flow<String> =
        context.prefsDataStore.data.map {
            it[ACCESS_TOKEN] ?: ""
        }
}