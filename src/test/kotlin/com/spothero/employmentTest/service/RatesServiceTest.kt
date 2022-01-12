package com.spothero.employmentTest.service

import com.nhaarman.mockitokotlin2.mock
import com.spothero.employmentTest.common.TimeUtils
import com.spothero.employmentTest.entity.RateEntity
import com.spothero.employmentTest.repository.RatesRepository
import org.assertj.core.api.KotlinAssertions.assertThat
import org.joda.time.LocalTime
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.ZonedDateTime


@Disabled()
internal class RatesServiceTest(
    @Autowired private val ratesRepository: RatesRepository
) {
    /**
     * If I were to have gotten this to work, I would have tested the following:
     *   1) If storing entities works by storing and counting (the PUT)
     *   2) If reading table entities produced proper output  (the GET)
     */
    @Test
    fun `GIVEN a list of entities WHEN save is attempted THEN entities saved`() {
        val mockRatesService = mock<RatesService>()
        ratesRepository.deleteAll()

        val tz = ZonedDateTime.now().zone.id
        val testRateEntity = RateEntity().copy(
            rate = 0,
            days = "mon, wed, fri",
            times = "0800-0900",
            tz = tz,
            localStartTime = "${
                TimeUtils.translateTimeToLocalTime(
                    LocalTime.now().minusMinutes(1).toString(), tz
                )
            }",
            localEndTime = "${
                TimeUtils.translateTimeToLocalTime(
                    LocalTime.now().plusMinutes(1).toString(), tz
                )
            }",
        )
        ratesRepository.save(
            testRateEntity
        )

        assertThat(ratesRepository.count()).isEqualTo(2)
    }

    @Test
    fun `are all entities retrieved`() {
        val subject = ratesRepository.findAll()
        assertThat(subject.size).isEqualTo(1)
    }
}