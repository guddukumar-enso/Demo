package com.infophone.home.domain.usecase

import com.infophone.home.domain.model.Chat
import com.infophone.home.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor (private val repository: ChatRepository) {
    suspend operator fun invoke(): List<Chat> {
        return repository.getChats()
    }
}