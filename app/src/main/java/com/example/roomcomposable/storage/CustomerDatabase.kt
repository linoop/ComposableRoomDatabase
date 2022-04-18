package com.example.roomcomposable.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomcomposable.models.Customer

@Database(entities = [Customer::class], version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile
        private var instance: CustomerDatabase? = null

        fun getInstance(context: Context): CustomerDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomerDatabase::class.java,
                        "customer_db"
                    ).build()
                    return instance as CustomerDatabase
                }
            } else {
                return instance as CustomerDatabase
            }
        }
    }
}