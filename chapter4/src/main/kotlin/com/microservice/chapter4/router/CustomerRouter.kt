package com.microservice.chapter4.router

import com.microservice.chapter4.Customer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.toMono

@Component
class CustomerRouter {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest { // functional 경로의 모든 요청을 처리
            "/customer".nest {
                GET("/") { it : ServerRequest ->
                    ok().body(Customer(1,"functaional web").toMono() ,
                            Customer::class.java)
                }
            }
        }
    }
}