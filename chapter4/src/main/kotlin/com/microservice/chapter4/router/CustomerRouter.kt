package com.microservice.chapter4.router

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {


    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest { // functional 경로의 모든 요청을 처리
            "/customer".nest {
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
                }
            "/customers".nest{
                GET("/", customerHandler::search)
                }
            }
        }
    }