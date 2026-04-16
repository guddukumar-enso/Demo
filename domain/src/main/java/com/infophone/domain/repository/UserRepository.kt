package com.infophone.domain.repository

import com.infophone.domain.model.User
import kotlinx.coroutines.flow.Flow

// Repository Interface - Contract defined in the Domain layer [2, 3]
interface UserRepository {
    // Uses suspend for one-shot operations [1]
    suspend fun getUserById(userId: String): User

    // Uses Flow for continuous data streams [1]
//    fun getAllUsers(): Flow<List<User>>
}