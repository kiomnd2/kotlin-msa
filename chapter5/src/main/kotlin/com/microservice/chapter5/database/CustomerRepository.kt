package com.microservice.chapter5.database

import com.microservice.chapter5.Customer
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.find
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.remove
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.query.Query
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import org.springframework.data.mongodb.core.query.Criteria.where
import javax.annotation.PostConstruct


@Repository //리엑티브 작업을 수행하는데 사용할수 있는 객체르 수신할 수 있다
class CustomerRepository (private val template: ReactiveMongoTemplate) {
    companion object {
        var initialCustomers = listOf(Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Customer.Telephone("+44", "74147412")))
    }

    @PostConstruct
    fun initializeRepository() = initialCustomers.map(Customer::toMono).map(this::create).map(Mono<Customer>::subscribe)

    fun create(customer: Mono<Customer>) = template.save(customer)

    fun findById(id : Int) = template.findById<Customer>(id)

    fun deleteById(id :Int) = template.remove<Customer>(Query(where("_id").isEqualTo(id)))

    fun findCustomer(nameFilter: String) = template.find<Customer>(Query(where("name").regex(".*nameFilter.*","i")))
}