package br.com.rodrigoamora.examplekoin.repository

import androidx.lifecycle.LiveData
import br.com.rodrigoamora.examplekoin.model.Contact

interface ContactRepository {
    fun getContacts(): LiveData<Resource<List<Contact>?>>
}