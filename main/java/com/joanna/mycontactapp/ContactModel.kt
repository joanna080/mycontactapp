package com.joanna.mycontactapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ContactModel(
    val name: String,
    val phoneNumber: String,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)