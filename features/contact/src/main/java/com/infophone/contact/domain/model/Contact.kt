package com.infophone.contact.domain.model

data class Contact(
    val id: Long,
    val name: String,
    val phone: String?,
    val photoUri: String?
)