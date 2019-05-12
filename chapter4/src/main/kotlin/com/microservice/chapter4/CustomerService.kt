package com.microservice.chapter4

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id :Int) : Mono<Customer>? // 게시자 등록
    fun searchCustomer(nameFilter : String) : Flux<Customer> // 여러개의 게시자 등록
    fun createCustomer(customerMono : Mono<Customer>) : Mono<*>
}