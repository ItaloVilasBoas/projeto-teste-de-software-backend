package com.movielist.core.exception

import com.movielist.core.model.dto.StandardError
import jakarta.servlet.http.HttpServletRequest
import com.movielist.core.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ApplicationExceptionsHandler {
    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception, request: HttpServletRequest): ResponseEntity<StandardError>{
        val err = StandardError()
        err.timestamp = Instant.now()
        err.status = HttpStatus.BAD_REQUEST.value()
        err.message = e.message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }
    @ExceptionHandler(NotFoundException::class)
    fun entityNotFound(e: NotFoundException, request: HttpServletRequest): ResponseEntity<StandardError> {
        val err = StandardError()
        err.timestamp = Instant.now()
        err.status = HttpStatus.BAD_REQUEST.value()
        err.message = e.message
        err.path = request.requestURI
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    @ExceptionHandler(NotAuthorizedException::class)
    fun notAuthorized(e: NotAuthorizedException, request: HttpServletRequest): ResponseEntity<StandardError> {
        val err = StandardError()
        err.timestamp = Instant.now()
        err.status = HttpStatus.UNAUTHORIZED.value()
        err.message = e.message
        err.path = request.requestURI
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    @ExceptionHandler(RegraNegocialNaoAtendidaException::class)
    fun regraNaoAtendida(e: RegraNegocialNaoAtendidaException, request: HttpServletRequest): ResponseEntity<StandardError> {
        val err = StandardError()
        err.timestamp = Instant.now()
        err.status = HttpStatus.CONFLICT.value()
        err.message = e.message
        err.path = request.requestURI
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }
}