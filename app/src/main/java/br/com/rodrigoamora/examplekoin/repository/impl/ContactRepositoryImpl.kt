package br.com.rodrigoamora.examplekoin.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.repository.ContactRepository
import br.com.rodrigoamora.examplekoin.repository.Resource

class ContactRepositoryImpl: ContactRepository {

    private val mediator = MediatorLiveData<Resource<List<Contact>?>>()

    override fun getContacts(): LiveData<Resource<List<Contact>?>> {
//        mediator.addSource(contactDao.findAll()) { contactsFound ->
//            mediator.value = Resource(contactsFound)
//        }
        return mediator
    }

}