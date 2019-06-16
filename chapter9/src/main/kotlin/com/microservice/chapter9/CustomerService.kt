package com.microservice.chapter9

interface CustomerService {
    fun getCustomer(id:Int) : Customer?
    fun getAllCustomers() : List<Customer>
}