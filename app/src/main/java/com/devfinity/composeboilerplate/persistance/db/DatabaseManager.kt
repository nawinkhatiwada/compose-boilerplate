package com.devfinity.composeboilerplate.persistance.db

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseManager @Inject constructor(context: Context) {
    private val instance = AppDatabase.getInstance(context)

    fun getInstance(): AppDatabase = instance

    fun getUserDao() = instance.getUserDao()
}