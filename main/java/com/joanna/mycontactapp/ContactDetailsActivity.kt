package com.joanna.mycontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joanna.mycontactapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView2.text = intent.getStringExtra("name")
        binding.textView3.text = intent.getStringExtra("number")


    }
}