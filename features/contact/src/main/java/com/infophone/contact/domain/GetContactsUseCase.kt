package com.infophone.contact.domain

import com.infophone.contact.domain.model.Contact
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(): List<Contact> = repository.getContacts()
}
