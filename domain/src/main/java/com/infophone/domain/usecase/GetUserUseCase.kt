package com.infophone.domain.usecase

import com.infophone.domain.model.User
import com.infophone.domain.repository.UserRepository


// Use Case (Interactor) - Depends only on the Domain interface [3]
class GetUserUseCase(private val userRepository: UserRepository) {
    // Encapsulates logic: fetching, validation, aggregation, etc.
    suspend operator fun invoke(userId: String): User {
        // Example: Add business logic validation here before fetching
        if (userId.isBlank()) {
            throw IllegalArgumentException("User ID cannot be blank")
        }
        return userRepository.getUserById(userId)
    }
}