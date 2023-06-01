package br.com.rodrigoamora.examplekoin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.rodrigoamora.examplekoin.database.dao.ContactDao
import br.com.rodrigoamora.examplekoin.model.Contact

class ContactRepository(
    private val contactDao: ContactDao
) {

    private val mediator = MediatorLiveData<Resource<List<Contact>?>>()

    fun getContacts(): LiveData<Resource<List<Contact>?>> {
        mediator.addSource(contactDao.findAll()) { contactsFound ->
            mediator.value = Resource(contactsFound)
        }
        return mediator
    }

}