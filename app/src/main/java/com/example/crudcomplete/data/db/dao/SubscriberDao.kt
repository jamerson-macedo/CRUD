package com.example.crudcomplete.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.crudcomplete.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDAO {

    @Insert
    fun insert(subscriber: SubscriberEntity): Long

    @Update
    fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM subscriber WHERE id = :id")
     fun delete(id: Long)

    @Query("DELETE FROM subscriber")
    fun deleteAll()

    @Query("SELECT * FROM subscriber")
    fun getAll(): LiveData<List<SubscriberEntity>>
}