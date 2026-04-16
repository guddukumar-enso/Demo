package com.infophone.auth.domain.usecase

import javax.inject.Inject

data class AuthUseCases @Inject constructor(
    val observeUser: ObserveUserUseCase,
    val saveUser: SaveUserUseCase,
)
