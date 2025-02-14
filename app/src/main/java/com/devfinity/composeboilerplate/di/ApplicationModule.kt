package com.devfinity.composeboilerplate.di

import android.app.Application
import android.content.Context
import com.devfinity.composeboilerplate.persistance.db.DatabaseManager
import com.devfinity.composeboilerplate.persistance.prefs.local.SharedPrefsLocal
import com.devfinity.composeboilerplate.persistance.prefs.local.SharedPrefsLocalImpl
import com.devfinity.composeboilerplate.persistance.prefs.SharedPrefManager
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProvider
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        AuthModule::class
    ]
)
class ApplicationModule {
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideSharedPreference(context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun provideSharePrefsRepository(prefManager: SharedPrefManager): SharedPrefsLocal {
        return SharedPrefsLocalImpl(prefManager)
    }

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider {
        return StringProviderImpl(context)
    }

    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return DatabaseManager(context)
    }
}