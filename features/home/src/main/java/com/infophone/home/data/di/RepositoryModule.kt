package com.infophone.home.data.di

import com.infophone.home.data.repository.ChatRepositoryImpl
import com.infophone.home.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// In :data module
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Bind the interface (ChatRepository) to the implementation (ChatRepositoryImpl)
    @Provides
    @Singleton
    fun provideChatRepository(impl: ChatRepositoryImpl): ChatRepository {
        return impl
    }
}