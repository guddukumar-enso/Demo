package com.infophone.contact.domain

import com.infophone.database.domain.FailedMessageRepository
import com.infophone.database.domain.model.FailedMessage

class SaveFailedMessageUseCase(
    private val repository: FailedMessageRepository
) {
    suspend operator fun invoke(message: FailedMessage) {
        repository.insertFailedMessage(message)
    }
}