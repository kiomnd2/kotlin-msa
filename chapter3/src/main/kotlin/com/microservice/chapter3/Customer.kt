package com.microservice.chapter3

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector

//리소스를 나타내는 클래스

@JsonInclude(Include.NON_NULL) // 널값인 데이터에 대해 아얘 안보이게 ...
data class Customer(var id: Int =0, var name: String = "", var telephone : Telephone?= null){
    data class Telephone(var countryCode :String ="", var telephoneNumber : String ="")
}
