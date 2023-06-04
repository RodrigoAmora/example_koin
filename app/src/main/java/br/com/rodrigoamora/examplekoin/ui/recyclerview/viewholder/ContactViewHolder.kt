package br.com.rodrigoamora.examplekoin.ui.recyclerview.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.examplekoin.R
import br.com.rodrigoamora.examplekoin.formatter.FormatterCellphone
import br.com.rodrigoamora.examplekoin.model.Contact

class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    lateinit var tvCellphoneContact: TextView
    lateinit var tvEmailContact: TextView
    lateinit var tvNameContact: TextView
    lateinit var deleteContact: ImageView

    fun setValues(contact: Contact) {
        tvCellphoneContact = itemView.findViewById<TextView>(R.id.tv_cellphone_contact)
        tvCellphoneContact.text = FormatterCellphone.format(contact.cellphone)

        tvEmailContact = itemView.findViewById<TextView>(R.id.tv_email_contact)
        tvEmailContact.text = contact.email

        tvNameContact = itemView.findViewById<TextView>(R.id.tv_name_contact)
        tvNameContact.text = contact.name

        deleteContact = itemView.findViewById<ImageView>(R.id.delete_contact)
    }

}