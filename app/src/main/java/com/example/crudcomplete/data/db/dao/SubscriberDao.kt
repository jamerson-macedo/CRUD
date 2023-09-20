package com.example.crudcomplete.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.crudcomplete.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDao{
    @Insert
    suspend fun insert(subscriberEntity: SubscriberEntity):Int
    @Update
    suspend fun Update(subscriberEntity: SubscriberEntity)
    @Query("DELETE FROM subscriber WHERE id=:id")
    suspend fun delete(id: Int)
    @Query("DELETE FROM subscriber")
    suspend fun deleteall()
    @Query("SELECT * FROM subscriber")
    fun getall():LiveData<List<SubscriberEntity>>
}