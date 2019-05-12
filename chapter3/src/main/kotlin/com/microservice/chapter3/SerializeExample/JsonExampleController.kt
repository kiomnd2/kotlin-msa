package com.microservice.chapter3.SerializeExample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExampleController {
    @GetMapping(value ="/json")
//    fun getJson() = SimpleObjectK()
    fun getJson() = ComplexObject(object1 = SimpleObjectK("more" , "complex"));
}