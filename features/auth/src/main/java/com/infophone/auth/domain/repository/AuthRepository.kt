package com.infophone.auth.domain.repository

import com.infophone.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeUser(): Flow<User?>
    suspend fun saveUser(user: User)
    suspend fun logout()
}
