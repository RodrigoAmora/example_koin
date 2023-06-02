package br.com.rodrigoamora.examplekoin.ui.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.recyclerview.viewholder.ContactViewHolder

class ContactAdapter(private val context: Context,
                     private val contacts: MutableList<Contact> = mutableListOf(),
                     var deleteContact: (contact: Contact) -> Unit = {},
                     var editContact: (contact: Contact) -> Unit = {}
): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.adapter_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.setValues(contacts[position])

        holder.deleteContact.setOnClickListener {
            deleteContact(contacts[position])
        }

        holder.tvNameContact.setOnClickListener {
            editContact(contacts[position])
        }
    }

    fun update(contacts: List<Contact>) {
        notifyItemRangeRemoved(0, this.contacts.size)
        this.contacts.clear()
        this.contacts.addAll(contacts)
        notifyItemRangeInserted(0, this.contacts.size)
    }

}