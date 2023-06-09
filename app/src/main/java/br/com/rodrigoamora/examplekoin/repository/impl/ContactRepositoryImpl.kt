package br.com.rodrigoamora.examplekoin.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.rodrigoamora.examplekoin.database.dao.ContactDao
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.repository.ContactRepository
import br.com.rodrigoamora.examplekoin.repository.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class ContactRepositoryImpl(
    private val contactDao: ContactDao
): ContactRepository {

    private val mediator = MediatorLiveData<Resource<List<Contact>?>>()

    override fun deleteContact(contact: Contact): LiveData<Resource<Void?>> {
        deleteInDatabase(contact)
        val liveData = MutableLiveData<Resource<Void?>>()
        liveData.value = Resource(null)
        return liveData
    }

    override fun getContacts(): LiveData<Resource<List<Contact>?>> {
        mediator.addSource(contactDao.findAll()) { contactsFound ->
            mediator.value = Resource(contactsFound)
        }

        return mediator
    }

    override fun saveContact(contact: Contact): LiveData<Resource<Void?>> {
        saveInDatabase(contact)
        val liveData = MutableLiveData<Resource<Void?>>()
        liveData.value = Resource(null)
        return liveData
    }

    private fun deleteInDatabase(contact: Contact) {
        CoroutineScope(IO).launch {
            contactDao.remove(contact)
        }
    }

    private fun saveInDatabase(contact: Contact) {
        CoroutineScope(IO).launch {
            contactDao.save(contact)
        }
    }

}