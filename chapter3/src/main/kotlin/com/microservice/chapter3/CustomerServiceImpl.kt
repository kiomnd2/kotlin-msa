package com.microservice.chapter3

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import com.microservice.chapter3.Customer.Telephone
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf( Customer(1, "Kotlin", Telephone("+44","1231222212")),
                Customer( 2 , "Spring"),
                Customer( 3 , "Micoroservice", Telephone("+12", "12312312321")))
    }
    val customers = ConcurrentHashMap<Int,Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        customers.remove(id)
        customers[id] = customer
    }

    override fun searchCustomers(nameFilter: String): List<Customer> =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>:: value).toList()

}