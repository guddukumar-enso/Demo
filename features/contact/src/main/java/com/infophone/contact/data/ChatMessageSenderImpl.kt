package com.infophone.contact.data

import com.infophone.contact.domain.ChatMessageSender

class ChatMessageSenderImpl(
    // private val api: ChatApi  // your Retrofit interface
) : ChatMessageSender {

    override suspend fun sendMessage(contactId: Long, text: String): Boolean {
        // TODO: replace with real API call
        // val response = api.sendMessage(SendMessageRequest(contactId, text))
        // return response.isSuccessful && response.body()?.success == true

        // For now, assume success:
        return true
    }
}