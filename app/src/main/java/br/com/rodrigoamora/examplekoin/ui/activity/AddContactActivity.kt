package br.com.rodrigoamora.examplekoin.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.extension.checkText
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity() {

    lateinit var inputCellphone: EditText
    lateinit var inputEmail: EditText
    lateinit var inputName: EditText

    private val contactViewModel: ContactViewModel by inject()//viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        initViews()
    }

    private fun initViews() {
        val btAddContact: Button = findViewById(R.id.bt_add_salon)
        btAddContact.setOnClickListener {
            saveContact()
        }

        inputCellphone = findViewById(R.id.tv_cellphone_contact)
        inputEmail = findViewById(R.id.tv_email_contact)
        inputName = findViewById(R.id.tv_name_contact)
    }

    private fun saveContact() {
        val inputs = listOf<EditText>(inputCellphone, inputEmail, inputName)
        if (verifyIfEditTextAreEmpty(inputs)) {
            val cellphone = inputCellphone.text.toString()
            val email = inputEmail.text.toString()
            val name = inputName.text.toString()

            val contact: Contact = Contact(0, name, cellphone, email)

            contactViewModel.saveContact(contact).observe(this,
                Observer {
                    Toast.makeText(this,
                                    getString(R.string.success_contact_save),
                                    Toast.LENGTH_LONG).show()

                    finish()
                }
            )
        }
    }

    private fun verifyIfEditTextAreEmpty(inputs: List<EditText>): Boolean {
        for (input in inputs) {
            if (!input.checkText(getString(R.string.error_field_required))) {
                return false
            }
        }
        return true
    }

}