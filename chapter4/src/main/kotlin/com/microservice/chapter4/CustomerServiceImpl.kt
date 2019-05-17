package com.microservice.chapter4

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import com.microservice.chapter4.Customer.Telephone
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono


@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf( Customer(1, "Kotlin", Telephone("+44","1231222212")),
                Customer( 2 , "Spring"),
                Customer( 3 , "Micoroservice", Telephone("+12", "12312312321")))
    }
    val customers = ConcurrentHashMap<Int,Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono()?: Mono.empty()

    override fun searchCustomer(nameFilter: String): Flux<Customer> =
            customers.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value ).toFlux()


    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer>  =
            customerMono.map {
                customers[it.id]= it // 반 객체0가 반환
                it //자신 반환
//                Mono.empty<Any>() // 빈객체를 명시적으로 변환
            }



}