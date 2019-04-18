package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class Chapter2Application

@Controller
class FirstController {

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type'=='simple' }")
    fun exampleService() : ServiceInterface =ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type'=='advance' }")
    fun advanceService() : ServiceInterface = AdvanceService()



    @Autowired
    lateinit var service2 : ExampleService2

    @RequestMapping(value="/user/{name}", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(@PathVariable name : String) = advanceService().getHello(name) +'\n' + service2.print()



}


fun main(args: Array<String>) {
    runApplication<Chapter2Application>(*args)
}
