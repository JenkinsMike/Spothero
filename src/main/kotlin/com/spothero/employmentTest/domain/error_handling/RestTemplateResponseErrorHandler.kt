package com.spothero.employmentTest.domain.error_handling

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.ResponseErrorHandler


/**
 * I modeled this after the API I work with.  We handle our errors to fail gracefully when
 * applicable.  I know that a lot of these are unused.  I over-wrote the code for this class.
 */
@Component
class RestTemplateResponseErrorHandler : ResponseErrorHandler {
    override fun hasError(response: ClientHttpResponse): Boolean {
        return (response.statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                response.statusCode.series() == HttpStatus.Series.SERVER_ERROR)
    }

    override fun handleError(response: ClientHttpResponse) {
        if (response.statusCode.series() == HttpStatus.Series.SERVER_ERROR) {
            throw HttpServerErrorException(response.statusCode)
        } else if (response.statusCode.series() == HttpStatus.Series.CLIENT_ERROR) {
            when (response.statusCode) {
                HttpStatus.NOT_FOUND ->
                    throw NoSuchElementException("No Match")
                HttpStatus.FORBIDDEN ->
                    throw CustomExceptionHandler(CustomException.FORBIDDEN)
                HttpStatus.UNAUTHORIZED ->
                    throw CustomExceptionHandler(CustomException.UNAUTHORIZED)
                else ->
                    throw CustomExceptionHandler(
                        CustomException.BAD_REQUEST,
                        HttpClientErrorException(response.statusCode)
                    )
            }

        }
    }
}
