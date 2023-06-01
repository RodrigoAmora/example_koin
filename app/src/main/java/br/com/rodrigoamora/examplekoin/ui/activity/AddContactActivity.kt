package br.com.rodrigoamora.examplekoin.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        val cellphone = inputCellphone.text.toString()
        val email = inputEmail.text.toString()
        val name = inputName.text.toString()

        val contact: Contact = Contact(0, name, cellphone, email)

        contactViewModel.saveContact(contact).observe(this,
            Observer {
                finish()
            }
        )
    }

}