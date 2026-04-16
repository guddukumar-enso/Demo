package com.infophone.database.di

import com.infophone.database.domain.model.BlockedUserRealm
import com.infophone.database.domain.model.ChatParticipantsRealm
import com.infophone.database.domain.model.FailedMessageRealm
import com.infophone.database.domain.model.GroupRealm
import com.infophone.database.domain.model.MessageRealm
import com.infophone.database.domain.model.MessageStatusRealm
import com.infophone.database.domain.model.UserRealm
import com.infophone.database.model.CallLogsRealm
import com.infophone.database.model.ChatRealm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    // 1. Provides the RealmConfiguration
    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        // You must list all your Realm DTOs here (e.g., UserRealmDto, ItemRealmDto)
        return RealmConfiguration.Builder(
            schema = setOf(
                ChatRealm::class,
                UserRealm::class,
                MessageRealm::class,
                MessageStatusRealm::class,
                BlockedUserRealm::class,
                CallLogsRealm::class,
                ChatParticipantsRealm::class,
                FailedMessageRealm::class,
                GroupRealm::class
            )
        )
            .schemaVersion(2) // Always increase this if schema changes
            .build()
    }

    // 2. Provides the actual Realm instance
    @Provides
    @Singleton
    fun provideRealm(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }
}

/*
fun setOfAllRealmClasses(): Set<KClass<out RealmObject>> {
    return setOf(
        ChatRealm::class
    )
}*/
