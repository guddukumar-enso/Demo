package com.infophone.contact.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infophone.contact.domain.GetContactsUseCase

class ContactsViewModelFactory(
    private val application: Application,
    private val getContactsUseCase: GetContactsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        /*if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            return ContactsViewModel(application, getContactsUseCase) as T
        }*/
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
