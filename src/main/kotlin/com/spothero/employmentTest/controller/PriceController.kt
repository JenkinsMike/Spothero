package com.spothero.employmentTest.controller

import com.spothero.employmentTest.common.TimeUtils.translateTimeToLocalDateTimeUtil
import com.spothero.employmentTest.domain.error_handling.CustomException
import com.spothero.employmentTest.domain.error_handling.CustomExceptionHandler
import com.spothero.employmentTest.service.PriceService
import com.spothero.employmentTest.view.PriceResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class PriceController(
    @Autowired private val priceService: PriceService
) {
    @Operation(
        description = "GET applicable price for requested time."
    )
    @Parameters(
        Parameter(name = "start", required = true, `in` = ParameterIn.QUERY),
        Parameter(name = "end", required = true, `in` = ParameterIn.QUERY),
    )
    @GetMapping(value = ["/v1/price"])
    @Throws(
        CustomExceptionHandler::class
    )
    fun getPrice(
        @RequestParam @Parameter(hidden = true) allParams: Map<String, String>
    ): PriceResponse {
        val startLocalDateTime: LocalDateTime = translateTimeToLocalDateTimeUtil(allParams["start"])
        val endLocalDateTime: LocalDateTime = translateTimeToLocalDateTimeUtil(allParams["end"])

        return if (startLocalDateTime.toLocalDate().dayOfMonth == endLocalDateTime.toLocalDate().dayOfMonth) {
            priceService.getPrice(
                startLocalDateTime,
                endLocalDateTime
            )
        } else if (endLocalDateTime < endLocalDateTime) {
            throw CustomExceptionHandler(
                CustomException.PRICE_UNAVAILABLE,
                RuntimeException("Parking end time is less than parking start time.")
            )
        } else {
            throw CustomExceptionHandler(
                CustomException.PRICE_UNAVAILABLE,
                RuntimeException("Parking stay spans more than one (1) day.")
            )
        }

    }
}
