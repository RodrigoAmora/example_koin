package br.com.rodrigoamora.examplekoin.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.extension.checkText
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import br.com.rodrigoamora.examplekoin.util.MaskUtil
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AddContactActivity : AppCompatActivity() {

    lateinit var inputCellphone: EditText
    lateinit var inputEmail: EditText
    lateinit var inputName: EditText

    private val contactViewModel: ContactViewModel by inject()

    private var contactId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        createToolbar()
        initViews()
        populateViews()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initViews() {
        val btAddContact: Button = findViewById(R.id.bt_add_salon)
        btAddContact.setOnClickListener {
            saveContact()
        }

        inputCellphone = findViewById(R.id.tv_cellphone_contact)
        inputCellphone.addTextChangedListener(MaskUtil.addMaskInEditText(inputCellphone, MaskUtil.FORMAT_PHONE))
        inputCellphone.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                handled = true
                inputEmail.requestFocus()
            }
            handled
        })

        inputEmail = findViewById(R.id.tv_email_contact)
        inputEmail.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handled = true
                saveContact()
            }
            handled
        })

        inputName = findViewById(R.id.tv_name_contact)
        inputName.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                handled = true
                inputCellphone.requestFocus()
            }
            handled
        })
    }

    private fun populateViews() {
        if (intent.extras != null) {
            val contact: Contact = intent.extras?.getSerializable("contact") as Contact
            contact.let {
                contactId = it.id
                inputName.setText(it.name)
                inputCellphone.setText(it.cellphone)
                inputEmail.setText(it.email)
            }
        }
    }

    private fun saveContact() {
        val inputs = listOf<EditText>(inputCellphone, inputEmail, inputName)
        if (verifyIfEditTextAreEmpty(inputs)) {
            val cellphone = inputCellphone.text.toString()
            val email = inputEmail.text.toString()
            val name = inputName.text.toString()

            val contact: Contact = Contact(contactId, name, cellphone, email)

            contactViewModel.saveContact(contact).observe(this,
                Observer {
                    val message = if (contactId > 0) {
                        getString(R.string.success_contact_edit)
                    } else {
                        getString(R.string.success_contact_save)
                    }

                    Toast.makeText(this,
                                    message,
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