package com.microservice.chapter9

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var customerService: CustomerService

    @Test
    fun MockMvc를_테스트해볼겁니다(){
    }

    @Test
    fun 우리는_특정아이디에_대한_고객을_GET으로_받아야합니다(){

        given(customerService.getCustomer(1))
                .willReturn(Customer(1, "mock customer"))

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.id").value(1))
                .andExpect(jsonPath("\$.name").value("mock customer"))
                .andDo(print())

        then(customerService).should(times(1)).getCustomer(1) //한번호출확인
        then(customerService).shouldHaveNoMoreInteractions() //그후에 고객과 어떤 상호작용도 없었음을 검증

        Mockito.reset(customerService)

    }

    @Test
    fun 우리는_고객리스트를_받아서_get인지확인한다(){

        given(customerService.getAllCustomers())
                .willReturn(listOf(Customer(1,"test"), Customer(2,"mock")))


        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk)
                .andExpect(jsonPath(("\$[0].id")).value(1))
                .andExpect(jsonPath(("\$[0].name")).value("test"))
                .andExpect(jsonPath(("\$[1].id")).value(2))
                .andExpect(jsonPath(("\$[1].name")).value("mock"))
                .andDo(print())

        then(customerService).should(times(1)).getAllCustomers() //한번호출확인
        then(customerService).shouldHaveNoMoreInteractions() //그후에 고객과 어떤 상호작용도 없었음을 검증

        Mockito.reset(customerService)

    }

}