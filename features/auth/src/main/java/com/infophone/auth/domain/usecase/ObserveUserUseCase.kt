package com.infophone.auth.domain.usecase

import com.infophone.auth.domain.model.User
import com.infophone.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<User?> =
        repository.observeUser()
}
