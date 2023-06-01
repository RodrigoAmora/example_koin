package br.com.rodrigoamora.examplekoin.ui.recyclerview.listener

interface OnItemRecyclerViewClickListener<T> {
    fun deleteItem(t: T)
    fun getItem(t: T)
}