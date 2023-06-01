package br.com.rodrigoamora.examplekoin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.repository.ContactRepository
import br.com.rodrigoamora.examplekoin.repository.Resource

class ContactViewModel(
    private val contactRepository: ContactRepository
): ViewModel() {



    fun getContacts(): LiveData<Resource<List<Contact>?>> {
        return contactRepository.getContacts()
    }

}