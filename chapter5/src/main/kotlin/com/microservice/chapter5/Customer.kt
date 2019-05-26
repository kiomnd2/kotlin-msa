package com.microservice.chapter5

import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "Customers") // 이 객체를 저장할 컬랙션의 이름
data class Customer (var id: Int =0, var name : String = "", var telephone : Telephone? =null) {
    data class Telephone ( var countryCode: String = "", var telephoneNumber: String ="")
}