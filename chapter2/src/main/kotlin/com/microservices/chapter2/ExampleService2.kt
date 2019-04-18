package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ExampleService2 {

    //7
    @Value(value = "#{4+3}")
    private lateinit var result1 : Number


    // one.value 나누기 another.value
    @Value(value = "#{ \${one.value} div \${another.value}  }")
    private lateinit var result2 : Number


    // one.value == another.value
    @Value(value="#{ \${one.value} eq \${another.value} }")
    private lateinit var result3 : Comparable<Boolean>

    // 이 값은 one.value 와 another.value 이어야 함
/*
    @Value(value="#{ \${one.value} and \${another.value} }")
    private lateinit var result4 : Comparable<Boolean>
*/


    // 이 값은 변수가 설정에 없으면 값이 hello로 설정됨
    @Value(value="\${service.message.simple:hello }")
    private lateinit var result5 : String

    // some.value 가 영어 또는 숫자이면  true
    @Value(value="#{ '\${some.value}' matches '[a-zA-Z\\s]+'}" )
    private lateinit var result6 : Comparable<Boolean>

    fun print() = "$result1 "+"$result2 "+"$result3  "+"$result5 "+"$result6"

}