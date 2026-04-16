package com.infophone.contact.domain

import com.infophone.database.domain.FailedMessageRepository


class ResendFailedMessagesUseCase(
    private val failedRepo: FailedMessageRepository,
    private val remoteSender: ChatMessageSender
) {
    /**
     * Resend all failed messages stored in Realm.
     * Returns list of IDs that were successfully synced.
     */
    suspend operator fun invoke(): List<String> {
        val failed = failedRepo.getFailedMessages()
        val successIds = mutableListOf<String>()

        for (msg in failed) {
            val ok = remoteSender.sendMessage(msg.contactId, msg.text)
            if (ok) {
                failedRepo.deleteFailedMessage(msg.id)
                successIds.add(msg.id)
            }
        }
        return successIds
    }
}