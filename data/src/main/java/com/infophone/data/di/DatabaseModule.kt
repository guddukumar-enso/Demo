package com.infophone.data.di

/*
import com.infophone.data.local.dto.UserRealmDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // 1. Provides the RealmConfiguration
    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        // You must list all your Realm DTOs here (e.g., UserRealmDto, ItemRealmDto)
        return RealmConfiguration.Builder(
            schema = setOf(UserRealmDto::class)
        )
            .schemaVersion(1) // Always increase this if schema changes
            .build()
    }

    // 2. Provides the actual Realm instance
    @Provides
    @Singleton
    fun provideRealm(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }
}*/
