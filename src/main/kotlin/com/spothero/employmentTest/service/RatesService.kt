package com.spothero.employmentTest.service

import com.spothero.employmentTest.common.TimeUtils.translateTimeToLocalTime
import com.spothero.employmentTest.entity.RateEntity
import com.spothero.employmentTest.repository.RatesRepository
import com.spothero.employmentTest.view.Rates
import com.spothero.employmentTest.view.RatesDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.reflect.Array.set
import java.util.*
import kotlin.collections.HashMap


@Service
class RatesService(
    @Autowired private val ratesRepository: RatesRepository
) {
    fun saveRates(ratesDetail: RatesDetail) {
        ratesRepository.deleteAll()

        ratesDetail.rates.forEach { rate ->
            val timeList = rate.times.split("-")
            val newRateEntity = RateEntity().copy(
                rate = rate.price,
                days = rate.days,
                times = rate.times,
                tz = rate.tz,
                localStartTime = "${translateTimeToLocalTime(timeList[0], rate.tz)}",
                localEndTime = "${translateTimeToLocalTime(timeList[1], rate.tz)}"
            )
            ratesRepository.save(
                newRateEntity
            )
        }
    }

    fun getRates(): RatesDetail {
        val rateEntityList: MutableList<RateEntity> = ratesRepository.findAll()
        val ratesDetail = RatesDetail()
        rateEntityList.forEach { rateEntity ->
            val rate = Rates(
                days = rateEntity.days,
                times = rateEntity.times,
                tz = rateEntity.tz,
                price = rateEntity.rate
            )
            ratesDetail.rates.add(rate)
        }

        return ratesDetail
    }
}
