package br.com.rodrigoamora.examplekoin.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import br.com.rodrigoamora.examplekoin.model.Contact

@Dao
interface ContactDao {
    @Query("DELETE FROM Contact")
    fun delete()

    @Query("SELECT * FROM Contact")
    fun findAll(): LiveData<List<Contact>>

    @Insert(onConflict = REPLACE)
    fun save(contact: Contact)

    @Insert(onConflict = REPLACE)
    fun save(contacts: List<Contact>)

    @Delete
    fun remove(contact: Contact)
}