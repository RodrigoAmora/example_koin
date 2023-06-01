package br.com.rodrigoamora.examplekoin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Contact {

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

    @ColumnInfo(name = "name")
    val name: String = ""

    @ColumnInfo(name = "cellphone")
    val cellphone: String = ""

    @ColumnInfo(name = "email")
    val email: String = ""
}