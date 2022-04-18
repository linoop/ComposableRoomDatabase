package com.example.roomcomposable.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomcomposable.models.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun fetchAllCustomers(): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Query("DELETE FROM customer WHERE id=:id")
    suspend fun deleteCustomerById(id: Int)

    @Query("DELETE FROM customer")
    suspend fun deleteAllCustomers()
}