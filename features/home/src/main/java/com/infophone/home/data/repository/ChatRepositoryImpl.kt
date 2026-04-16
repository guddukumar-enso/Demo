package com.infophone.home.data.repository

import com.infophone.home.data.api.ChatApi
import com.infophone.home.data.mapper.toDomainChatList
import com.infophone.home.domain.model.Chat
import com.infophone.home.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApi: ChatApi
) : ChatRepository {
    override suspend fun getChats(): List<Chat> {
        // 1. Fetch DTO from Ktor Data Source
        val chatDto = chatApi.fetchChat()

        // 2. Map DTO to Domain Entity before returning [5]
        return chatDto.toDomainChatList()
    }
}