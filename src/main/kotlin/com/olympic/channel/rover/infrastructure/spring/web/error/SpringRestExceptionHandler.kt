package com.olympic.channel.rover.infrastructure.spring.web.error

import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice(basePackages = ["com.olympic.channel.rover.infrastructure.spring.web"])
class SpringRestExceptionHandler : ResponseEntityExceptionHandler() {

        @ExceptionHandler(BadFormatException::class)
        fun badFormatException(e: BadFormatException): ResponseEntity<ErrorDto> {
            val error = ErrorDto(e.reason)
            return ResponseEntity.badRequest().body(error)
        }

    }