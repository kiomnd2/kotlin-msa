package com.microservice.chapter4.router

import com.microservice.chapter4.Customer
import org.omg.CORBA.ServerRequest
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class CustomerHandler {
}