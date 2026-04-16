package com.infophone.contact.domain

interface ChatMessageSender {
    /**
     * Send a message to server for a given contact.
     * Return true if server ack'd successfully.
     */
    suspend fun sendMessage(contactId: Long, text: String): Boolean
}