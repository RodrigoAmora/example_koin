package br.com.rodrigoamora.examplekoin.repository

import androidx.lifecycle.LiveData
import br.com.rodrigoamora.examplekoin.model.Contact

interface ContactRepository {
    fun deleteContact(contact: Contact): LiveData<Resource<Void?>>
    fun getContacts(): LiveData<Resource<List<Contact>?>>
    fun saveContact(contact: Contact): LiveData<Resource<Void?>>
}