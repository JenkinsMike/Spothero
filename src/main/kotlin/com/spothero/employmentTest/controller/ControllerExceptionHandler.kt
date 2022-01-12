package com.spothero.employmentTest.controller

import com.spothero.employmentTest.domain.error_handling.CustomExceptionHandler
import com.spothero.employmentTest.domain.error_handling.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


/**
 * I modeled this after the API I work with.  We handle our errors to fail gracefully when
 * applicable.  I know that a lot of these are unused.  I over-wrote the code for this class.
 *
 * Basically, it catches exceptions as bubbled up to the Controller and wraps them in custom
 * messaging.
 */
@RestControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleBadRequest(responseBody: ExceptionResponse): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    private fun handleForbiddenAccess(responseBody: ExceptionResponse): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(responseBody, HttpStatus.FORBIDDEN)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private fun handleNotFound(responseBody: ExceptionResponse): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(responseBody, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ResponseBody
    private fun handleUnsupportedOperation(responseBody: ExceptionResponse): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(responseBody, HttpStatus.I_AM_A_TEAPOT)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    private fun handleConflict(responseBody: ExceptionResponse): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(responseBody, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [CustomExceptionHandler::class])
    protected fun handleCustomExceptions(
        e: CustomExceptionHandler,
        request: WebRequest? = null
    ): ResponseEntity<ExceptionResponse> {
        val bodyOfResponse = ExceptionResponse(
            internalStatus = e.definedException.errorCode,
            internalException = e.serviceThrownException?.message,
            customException = e.definedException.message,
            solution = e.definedException.possibleSolution
        )
        when (e.definedException.errorCode) {
            400 -> return handleBadRequest(bodyOfResponse)
            403 -> return handleForbiddenAccess(bodyOfResponse)
            404 -> return handleNotFound(bodyOfResponse)
            418 -> return handleUnsupportedOperation(bodyOfResponse)
            500 -> return handleConflict(bodyOfResponse)
        }

        return handleUnsupportedOperation(bodyOfResponse)
    }
}
