package com.infophone.database.domain

import com.infophone.database.domain.model.FailedMessage

interface FailedMessageRepository {
    suspend fun insertFailedMessage(message: FailedMessage)
    suspend fun getFailedMessages() : List<FailedMessage>

    suspend fun deleteFailedMessage(id: String)
}