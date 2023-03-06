package com.nextup.bulkybookapp.di

import android.app.Application
import com.nextup.bulkybookapp.Utils.DataStore
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
object Module {



    @Provides
    @Singleton
    fun provideDataStore(app: Application): DataStore {
        return DataStore(app)
    }



}