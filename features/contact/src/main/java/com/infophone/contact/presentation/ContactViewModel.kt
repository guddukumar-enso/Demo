package com.infophone.contact.presentation

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.contact.domain.model.Contact
import com.infophone.contact.domain.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.isNotEmpty

data class ContactsUiState(
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contentResolver: ContentResolver,
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState: StateFlow<ContactsUiState> = _uiState

    private var observerJob: Job? = null

    private val contactsObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            observerJob?.cancel()
            observerJob = viewModelScope.launch {
                delay(500)
                loadContacts(force = true)
            }
        }
    }

    init {
        registerObserver()
    }

    private fun registerObserver() {
       // val resolver = .contentResolver
        contentResolver.registerContentObserver(
            ContactsContract.Contacts.CONTENT_URI,
            true,
            contactsObserver
        )
    }

    override fun onCleared() {
        super.onCleared()
        //val resolver = applicationContext.contentResolver
        contentResolver.unregisterContentObserver(contactsObserver)
        observerJob?.cancel()
    }

    fun loadContacts(force: Boolean = false) {
        if (!force && _uiState.value.contacts.isNotEmpty()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val contacts = getContactsUseCase()
                _uiState.value = _uiState.value.copy(
                    contacts = contacts,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to load contacts"
                )
            }
        }
    }
}