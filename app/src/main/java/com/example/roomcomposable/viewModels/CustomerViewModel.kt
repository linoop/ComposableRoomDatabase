package com.example.roomcomposable.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomcomposable.models.Customer
import com.example.roomcomposable.repository.CustomerRepo
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private val customerRepo = CustomerRepo(application)

    fun fetchAllCustomer() = customerRepo.readAllCustomers

    fun insertCustomer(customer: Customer) = viewModelScope.launch {
        customerRepo.insertCustomer(customer = customer)
    }

    fun deleteCustomerById(id: Int) = viewModelScope.launch {
        customerRepo.deleteCustomerById(id)
    }
}