package com.soten.search.data.di

import android.content.Context
import com.soten.search.data.db.AppDatabase
import com.soten.search.data.db.AppDatabase.Companion.getInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) = getInstance(context)

    @Provides
    @Singleton
    fun providesMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()
}