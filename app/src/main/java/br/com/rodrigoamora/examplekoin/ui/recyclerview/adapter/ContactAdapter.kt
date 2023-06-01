package br.com.rodrigoamora.examplekoin.ui.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.model.Contact
import br.com.rodrigoamora.examplekoin.ui.recyclerview.listener.OnItemRecyclerViewClickListener
import br.com.rodrigoamora.examplekoin.ui.recyclerview.viewholder.ContactViewHolder

class ContactAdapter(context: Context, contacts: List<Contact>): RecyclerView.Adapter<ContactViewHolder>() {

    private val context: Context = context
    var contacts: List<Contact> = contacts

    private var listener: OnItemRecyclerViewClickListener<Contact>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.adapter_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.setValues(contacts[position])

        holder.deleteContact.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View) {
                listener?.deleteItem(contacts[position])
            }
        })

        holder.tvNameContact.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View) {
                listener?.getItem(contacts[position])
            }
        })
    }

    fun setListener(listener: OnItemRecyclerViewClickListener<Contact>?) {
        this.listener = listener
    }

}