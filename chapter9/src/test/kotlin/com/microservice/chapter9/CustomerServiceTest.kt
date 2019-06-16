package com.microservice.chapter9

import org.amshove.kluent.`should not be null`
import org.amshove.kluent.shouldNotBeNull
import org.junit.Assert
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    lateinit var customerService: CustomerService

    @Test
    fun 고객리스트_확인하고_예상한_사이즈가_맞는가() {
        var customers = customerService.getAllCustomers()
        Assert.assertEquals(customers.size,3 )

    }

    @Test
    fun 고객_아이디를_입력받고_검증한다() {
        var customer = customerService.getCustomer(1)
        customer.`should not be null`()
        Assert.assertEquals(customer?.name, "Kotlin")
    }
}