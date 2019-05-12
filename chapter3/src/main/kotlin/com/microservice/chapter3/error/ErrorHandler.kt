package com.microservice.chapter3.error

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)  //JSON Parser Exception이 발생할 때..
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("JSONError", exception.message ?:
        "invalid json"),HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(CustomNotFoundException::class)
    fun CustomNotFoundExceptionHandler(servletRequest: HttpServletRequest, exception: Exception)
    : ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Customer Not Found", exception.message!! ), HttpStatus.NOT_FOUND)
    }
}