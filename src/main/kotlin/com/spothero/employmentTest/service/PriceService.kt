package com.spothero.employmentTest.service

import com.spothero.employmentTest.domain.error_handling.CustomException
import com.spothero.employmentTest.domain.error_handling.CustomExceptionHandler
import com.spothero.employmentTest.entity.RateEntity
import com.spothero.employmentTest.repository.RatesRepository
import com.spothero.employmentTest.view.PriceResponse
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PriceService(
    @Autowired private val ratesRepository: RatesRepository
) {
    @Throws(
        CustomExceptionHandler::class
    )
    fun getPrice(
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): PriceResponse {
        val dayOfWeek = startTime.dayOfWeek().asShortText
        //val endDayOfWeek = endTime.dayOfWeek().asShortText
        val startLocalDateTime: LocalTime = startTime.toLocalTime()
        val endLocalDateTime: LocalTime = endTime.toLocalTime()

        val rateEntityList: List<RateEntity>? =
            ratesRepository.findByLocalEndTimeGreaterThanEqualAndLocalStartTimeLessThanEqual(
                "$endLocalDateTime",
                "$startLocalDateTime"
            )
        val rateEntityListParsedByDay: List<RateEntity>? = rateEntityList?.filter {
            it.days.contains(dayOfWeek, true)
        }

        if (rateEntityListParsedByDay != null) {
            when {
                rateEntityListParsedByDay.size > 1 -> {
                    throw CustomExceptionHandler(
                        CustomException.PRICE_UNAVAILABLE,
                        RuntimeException("Parking stay spans more than one (1) rate.")
                    )
                }
                rateEntityListParsedByDay.isEmpty() -> {
                    throw CustomExceptionHandler(CustomException.PRICE_UNAVAILABLE, RuntimeException("No valid rate."))
                }
            }
        }

        return PriceResponse(price = rateEntityListParsedByDay?.get(0)?.rate ?: 0)
    }
}
