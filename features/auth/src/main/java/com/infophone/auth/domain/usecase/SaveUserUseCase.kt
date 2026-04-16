package com.infophone.auth.domain.usecase

import com.infophone.auth.domain.model.User
import com.infophone.auth.domain.model.merge
import com.infophone.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: AuthRepository
)      {


    suspend operator fun invoke(partial: User) {
        val current = repository.observeUser().firstOrNull()
        val merged = current?.merge(partial) ?: partial
        repository.saveUser(merged)
    }
}