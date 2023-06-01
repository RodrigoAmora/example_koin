package br.com.rodrigoamora.examplekoin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    var cellphone: String ,
    var email: String = ""
): Serializable
