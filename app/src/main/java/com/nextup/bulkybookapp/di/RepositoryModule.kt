package com.nextup.bulkybookapp.di

import com.nextup.bulkybookapp.data.Repository.AuthRepository
import com.nextup.bulkybookapp.domain.network.Service
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@dagger.Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(service: Service): AuthRepository {
        return  AuthRepository(service)
    }

}