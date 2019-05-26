package com.microservice.chapter5

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun createCustomer(customer: Mono<Customer>) : Mono<Customer>
    fun getCustomer(id: Int) : Mono<Customer>
    fun deleteCustomer(id :Int) : Mono<Boolean>
    fun searchCustomers(nameFfilter: String): Flux<Customer>
}