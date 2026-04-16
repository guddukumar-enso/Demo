package com.infophone.home.domain.repository

import com.infophone.home.domain.model.Chat

interface ChatRepository {
    suspend fun getChats(): List<Chat>
}