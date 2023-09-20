package com.example.crudcomplete.data.db.repository

import androidx.lifecycle.LiveData
import com.example.crudcomplete.data.db.entity.SubscriberEntity

interface SubscriberRepository {
    suspend fun insertSubscriber(nome: String, email: String): Int
    suspend fun updateSubscriber(id: Int, nome: String, email: String)
    suspend fun deleteSubscriber(id: Int)
    suspend fun deleteallSubscriber()
    suspend fun getallSubscriber(): LiveData<List<SubscriberEntity>>

}