package com.joanna.mycontactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joanna.mycontactapp.databinding.ContactItemBinding


class MyContactAdapter(val contactItems: List<ContactModel>,
                     val clickerFnx: (ContactModel) -> Unit):
    RecyclerView.Adapter<MyContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ContactItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(contactItem: ContactModel){
            binding.ciName.text = contactItem.name
            binding.ciNumber.text = contactItem.phoneNumber
            binding.root.setOnClickListener {
                clickerFnx(contactItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactItemBinding =
            ContactItemBinding.inflate(LayoutInflater.from(parent.context))

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactItems.get(position))

    }

    override fun getItemCount() = contactItems.size
}