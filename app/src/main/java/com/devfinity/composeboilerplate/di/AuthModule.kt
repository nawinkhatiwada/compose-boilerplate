package com.devfinity.composeboilerplate.di

import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.features.auth.data.AuthRepositoryImpl
import com.devfinity.composeboilerplate.features.auth.data.local.AuthLocalImpl
import com.devfinity.composeboilerplate.features.auth.data.remote.AuthRemoteImpl
import com.devfinity.composeboilerplate.persistance.prefs.SharedPrefManager
import com.devfinity.composeboilerplate.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AuthModule {
    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPrefManager,
    ): AuthRepository.Local {
        return AuthLocalImpl(sharedPreferenceManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): AuthRepository.Remote {
        return AuthRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        localRepository: AuthRepository.Local,
        remoteRepository: AuthRepository.Remote
    ): AuthRepository {
        return AuthRepositoryImpl(localRepository, remoteRepository)
    }
}