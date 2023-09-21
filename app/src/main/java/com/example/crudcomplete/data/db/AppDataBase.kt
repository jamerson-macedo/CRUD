package com.example.crudcomplete.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crudcomplete.data.db.dao.SubscriberDAO

import com.example.crudcomplete.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract val subscriberDao: SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getinstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "app_database").allowMainThreadQueries().build()
                }
                return instance
            }

        }
    }
}