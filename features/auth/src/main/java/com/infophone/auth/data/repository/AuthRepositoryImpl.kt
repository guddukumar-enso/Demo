package com.infophone.auth.data.repository

import com.infophone.database.datasource.AuthLocalDataSource
import com.infophone.auth.data.mapper.toDomain
import com.infophone.auth.data.mapper.toEntity
import com.infophone.auth.domain.model.User
import com.infophone.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun observeUser(): Flow<User?> =
        localDataSource.observeUser().map { it?.toDomain() }

    override suspend fun saveUser(user: User) {
        localDataSource.saveUser(user.toEntity())
    }

    override suspend fun logout() {
        localDataSource.clearUser()
    }
}
