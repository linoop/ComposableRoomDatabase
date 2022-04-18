package com.example.roomcomposable.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomcomposable.models.Customer
import com.example.roomcomposable.storage.CustomerDao
import com.example.roomcomposable.storage.CustomerDatabase

class CustomerRepo(application: Application) {
    private var customerDao: CustomerDao

    init {
        val customerDatabase = CustomerDatabase.getInstance(application)
        customerDao = customerDatabase.customerDao()
    }

    val readAllCustomers: LiveData<List<Customer>> = customerDao.fetchAllCustomers()

    suspend fun insertCustomer(customer: Customer) {
        customerDao.insertCustomer(customer = customer)
    }

    suspend fun deleteCustomerById(id: Int) {
        customerDao.deleteCustomerById(id)
    }

    suspend fun deleteAllCustomer() {
        customerDao.deleteAllCustomers()
    }
}