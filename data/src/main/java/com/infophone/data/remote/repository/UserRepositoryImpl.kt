package com.infophone.data.remote.repository

import com.infophone.data.remote.mapper.toDomainUser
import com.infophone.data.remote.api.UserApi
import com.infophone.domain.model.User
import com.infophone.domain.repository.UserRepository
import javax.inject.Inject

// Repository Implementation - Implements the Domain contract [3]
class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): User {
        // 1. Fetch DTO from Ktor Data Source
        val userDto = userApi.fetchUser(userId)

        // 2. Map DTO to Domain Entity before returning [5]
        return userDto.toDomainUser()
    }

    /*override fun getAllUsers(): Flow<List<User>> {

    }*/

    //... implementation for getAllUsers() Flow...
}