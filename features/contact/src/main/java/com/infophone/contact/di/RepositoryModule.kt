package com.infophone.contact.di

import android.content.ContentResolver
import android.content.Context
import com.infophone.contact.data.ContactsRepositoryImpl
import com.infophone.contact.domain.ContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton // Or the appropriate scope
    fun provideContentResolver(@ApplicationContext appContext: Context): ContentResolver {
        return appContext.contentResolver
    }

    @Provides
    @Singleton
    fun bindContactRepository(impl: ContactsRepositoryImpl): ContactsRepository{
        return impl
    }
}
