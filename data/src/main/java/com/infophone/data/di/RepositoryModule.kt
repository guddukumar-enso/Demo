package com.infophone.data.di

//import com.infophone.data.local.repository.UserLocalRepositoryImpl
import com.infophone.data.remote.repository.PostRepositoryImpl
import com.infophone.data.remote.repository.UserRepositoryImpl
import com.infophone.domain.repository.PostRepository
import com.infophone.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The @Binds method establishes the mapping: the abstract function's return type must be
 * the Domain Layer’s Interface (UserRepository), and the single parameter must be the Data
 * Layer’s Implementation (UserRepositoryImpl).
 *
 * This ensures that any Use Case requesting UserRepository receives the concrete
 * UserRepositoryImpl instance, enabling the application to function without the Domain layer
 * ever needing to know the Data layer's concrete package structure.
 */

@Module
@InstallIn(SingletonComponent::class)
// Abstract module for binding interface to implementation [6]
abstract class RepositoryModule {

    @Binds
    @Singleton
    // Hilt sees the return type (Interface) and binds the parameter (Implementation)
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    /*@Binds
    @Singleton
    abstract fun bindUserLocalRepository(userLocalRepositoryImpl: UserLocalRepositoryImpl): UserRepository*/

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}