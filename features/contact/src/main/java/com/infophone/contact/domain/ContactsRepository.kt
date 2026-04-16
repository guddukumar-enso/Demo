package com.infophone.contact.domain

import com.infophone.contact.domain.model.Contact

interface ContactsRepository {
    suspend fun getContacts(): List<Contact>
}
