package com.spothero.employmentTest.domain.error_handling

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * I modeled this after the API I work with.  We handle our errors to fail gracefully when
 * applicable.  I know that a lot of these are unused.  I over-wrote the code for this class.
 *
 * Basically, it handles Exceptions with custom messaging.
 */
class CustomExceptionHandler(
    exception: CustomException,
    internalException: Exception? = null
) : Exception(exception.message) {
    val definedException = exception
    val serviceThrownException = internalException
}

enum class CustomException(
    val message: String,
    val errorCode: Int,
    val possibleSolution: String
) {
    BAD_REQUEST("Bad Request", 400, "Try with appropriate request params."),
    UNAUTHORIZED("Access Unauthorized", 401, "Contact Mike Jenkins for instructions."),
    FORBIDDEN("Access Forbidden", 403, "Contact Mike Jenkins for instructions."),

    PRICE_UNAVAILABLE("Price Unavailable", 400, "Check and shorten the hours and limit to one (1) day.");

    companion object {
        fun fromErrorCode(errorCode: Int): List<CustomException> =
            values().filter {
                it.errorCode == errorCode
            }

        fun getExceptionResponses(errorCode: Int): List<ExceptionResponse> =
            values().filter {
                it.errorCode == errorCode
            }.map {
                ExceptionResponse(
                    internalStatus = it.errorCode,
                    customException = it.name,
                    internalException = it.message,
                    solution = it.possibleSolution
                )
            }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExceptionResponse(
    @JsonProperty(value = "internalStatus") val internalStatus: Int,
    @JsonProperty(value = "customException") val customException: String,
    @JsonProperty(value = "message") val internalException: String?,
    @JsonProperty(value = "try") val solution: String,
)
