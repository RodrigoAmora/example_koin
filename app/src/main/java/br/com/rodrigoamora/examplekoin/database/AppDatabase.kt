package br.com.rodrigoamora.examplekoin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rodrigoamora.examplekoin.database.dao.ContactDao
import br.com.rodrigoamora.examplekoin.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

}