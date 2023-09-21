package com.example.crudcomplete.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber")
data class SubscriberEntity(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val emaIL: String
)