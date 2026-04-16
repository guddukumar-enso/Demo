package com.infophone.database.domain.model

data class FailedMessage(
    val id: String,
    val contactId: Long,
    val text: String,
    val timestamp: Long,
    val reason: String?
)
