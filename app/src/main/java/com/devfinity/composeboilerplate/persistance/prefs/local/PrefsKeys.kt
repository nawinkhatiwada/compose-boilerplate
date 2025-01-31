package com.devfinity.composeboilerplate.persistance.prefs.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefsKeys {
    const val PREF_NAME = "devfinity_prefs"
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
}