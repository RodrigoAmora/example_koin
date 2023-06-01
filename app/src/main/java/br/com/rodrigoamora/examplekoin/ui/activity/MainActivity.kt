package br.com.rodrigoamora.examplekoin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.database.dao.ContactDao
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.recyclerview.adapter.ContactAdapter
import br.com.rodrigoamora.examplekoin.ui.recyclerview.listener.OnItemRecyclerViewClickListener
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var contacts: List<Contact>

    private val contactViewModel: ContactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

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
        if (!contacts.isNullOrEmpty()) {
            val adapter = ContactAdapter(this, contacts)
            adapter.setListener(object : OnItemRecyclerViewClickListener<Contact> {
                override fun deleteItem(contact: Contact) {

                }

                override fun getItem(contact: Contact) {
                    val bundle = Bundle()
                    bundle.putSerializable("contact", contact)

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