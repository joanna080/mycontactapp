package com.joanna.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.joanna.mycontactapp.databinding.ActivityMainBinding

const val NAME_KEY = "NAME_KEY"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myContactAdapter: MyContactAdapter
    private lateinit var myContactList: MutableList<ContactModel>
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        myContactList = mutableListOf()

        myContactAdapter = MyContactAdapter(myContactList){
            val intent = Intent(this, ContactDetailsActivity::class.java)
            intent.putExtra("name", it.name)
            intent.putExtra("number",it.phoneNumber)
            startActivity(intent)
        }
        binding.recyclerView.adapter = myContactAdapter

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java, "contact-database"
        ).allowMainThreadQueries().build()


        viewModel.getAllContactItems(db).observe(this, {
            myContactAdapter = MyContactAdapter(it){
                val intent = Intent(this, ContactDetailsActivity::class.java)
                intent.putExtra("name", it.name)
                intent.putExtra("number",it.phoneNumber)

                startActivity(intent)
            }
            binding.recyclerView.adapter = myContactAdapter
            myContactAdapter.notifyDataSetChanged()
        })



        binding.caButton.setOnClickListener {
            val name: String = binding.caName.text.toString()
            val phoneNumber: String = binding.caNumber.text.toString()
            //val email: String = binding.caEmail.text.toString()



            val contactItem = ContactModel(name, phoneNumber)
            viewModel.addContactItem(contactItem, db)

            myContactAdapter.notifyDataSetChanged()
        }

    }
}