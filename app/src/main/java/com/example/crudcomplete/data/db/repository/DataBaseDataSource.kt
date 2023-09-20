package com.example.crudcomplete.data.db.repository

import androidx.lifecycle.LiveData
import com.example.crudcomplete.data.db.dao.SubscriberDao
import com.example.crudcomplete.data.db.entity.SubscriberEntity

class DataBaseDataSource(private val subscriberDao: SubscriberDao) : SubscriberRepository {
    override suspend fun insertSubscriber(nome: String, email: String): Int {
        val subscriber = SubscriberEntity(name = nome, emaIL = email)
        return subscriberDao.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Int, nome: String, email: String) {
       val subscriber=SubscriberEntity(id = id, name = nome, emaIL = email)
        subscriberDao.Update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Int) {
        subscriberDao.delete(id)
    }

    override suspend fun deleteallSubscriber() {
      subscriberDao.deleteall()
    }

    override suspend fun getallSubscriber(): LiveData<List<SubscriberEntity>> {
       return subscriberDao.getall()
    }
}