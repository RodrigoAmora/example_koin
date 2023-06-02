package br.com.rodrigoamora.examplekoin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.recyclerview.adapter.ContactAdapter
import br.com.rodrigoamora.examplekoin.ui.recyclerview.listener.OnItemRecyclerViewClickListener
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import br.com.rodrigoamora.examplekoin.util.PackageInfoUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val contactViewModel: ContactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        configureRecyclerView()
        getContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                showAlert()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlert() {
        val message = getString(R.string.created_by_rodrigo_amora)+"\n"+
                        getString(R.string.email_rodrigo_amora)+"\n"+
                        getString(R.string.version_app, PackageInfoUtil.getVersionName(this))

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun initViews() {
        val fabAddContact: FloatingActionButton = findViewById(R.id.fab_add_contact)
        fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        recyclerView = findViewById(R.id.list_contacts)
    }

    private fun configureRecyclerView() {
        val linearLayout = LinearLayoutManager(this,
                                                LinearLayoutManager.VERTICAL,
                                                false)
        val dividerItemDecoration = DividerItemDecoration(this,
                                                            DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = linearLayout
        recyclerView.isNestedScrollingEnabled = false
    }

    private fun populateRecyclerView(contacts: List<Contact>) {
        if (contacts.isNotEmpty()) {
            val adapter = ContactAdapter(this, contacts)
            recyclerView.adapter = adapter

            adapter.setListener(object : OnItemRecyclerViewClickListener<Contact> {
                override fun deleteItem(contact: Contact) {
                    contactViewModel.delete(contact).observe(this@MainActivity,
                        Observer {
                            Toast.makeText(this@MainActivity,
                                getString(R.string.success_contact_delete),
                                Toast.LENGTH_LONG).show()
                        }
                    )
                }

                override fun getItem(contact: Contact) {
                    val intent = Intent(this@MainActivity, AddContactActivity::class.java)
                    intent.putExtra("contact", contact)
                    startActivity(intent)
                }
            })
        }
    }

    private fun getContacts() {
        contactViewModel.getContacts().observe(this, Observer { contacts ->
            contacts.result?.let { populateRecyclerView(it) }
        })
    }

}