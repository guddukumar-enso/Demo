package com.infophone.group.presentation.viewModal


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.infophone.contact.domain.model.Contact

class SelectUserViewModal : ViewModel() {

    var contacts = mutableStateListOf<Contact>()

    // Search text state
    private val _search = mutableStateOf("")
    val search: String get() = _search.value
    // Selected contacts state
    private val _selectedContacts = mutableStateListOf<Contact>()
    val selectedContacts: List<Contact> get() = _selectedContacts

    fun onSearchChange(value: String) {
        _search.value = value
    }

    fun toggleContact(contact: Contact) {
        if (_selectedContacts.any { it.id == contact.id }) {
            _selectedContacts.removeAll { it.id == contact.id }
        } else {
            _selectedContacts.add(contact)
        }
    }

    fun isContactSelected(contact: Contact): Boolean {
        return _selectedContacts.any { it.id == contact.id }
    }
}
