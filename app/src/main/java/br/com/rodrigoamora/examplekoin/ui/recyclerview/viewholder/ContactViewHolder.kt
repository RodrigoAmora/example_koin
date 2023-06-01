package br.com.rodrigoamora.examplekoin.ui.recyclerview.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.model.Contact

class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    lateinit var tvNameContact: TextView
    lateinit var deleteContact: ImageView

    fun setValues(contact: Contact) {
        tvNameContact = itemView.findViewById<TextView>(R.id.tv_name_salon)
        tvNameContact.text = contact.name

        deleteContact = itemView.findViewById<ImageView>(R.id.delete_salon)
    }

}