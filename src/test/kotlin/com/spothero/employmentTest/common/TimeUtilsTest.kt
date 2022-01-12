package com.spothero.employmentTest.common

import com.spothero.employmentTest.common.TimeUtils.translateTimeToLocalDateTimeUtil
import com.spothero.employmentTest.common.TimeUtils.translateTimeToLocalTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime


internal class TimeUtilsTest {
    /**
     * How to test this eluded me.  I could write tests to work now,
     * but then when we "spring ahead" again they would break.
     *
     * There were some articles I skimmed that outlined some pretty
     * specific methodologies to follow.  I am going to concentrate
     * on other tests.  This is a testing strategy I would want to
     * learn from a codebase that already has this test locked down.
     *
     * I will write a couple though...
     */
    @Test
    fun `GIVEN call to translateTimeToLocalDateTimeUtil THEN proper translated time is returned`() {
        val baseTime = "2020-06-29T07:00:00-07:00"
        val baseTz = ZonedDateTime.now().zone.id
        val subjectOne = translateTimeToLocalDateTimeUtil(
            baseTime,
            baseTz
        )
        val subjectTwo = translateTimeToLocalDateTimeUtil(
            baseTime
        )
        assertThat(subjectOne).isEqualTo(subjectTwo)
    }

    @Test
    fun `GIVEN call to translateTimeToLocalTime THEN proper translated time is returned`() {
        val baseTime = "0211"
        val baseTz = ZonedDateTime.now().zone.toString()

        val subjectOne = translateTimeToLocalTime(
            baseTime,
            baseTz
        )
        val subjectTwo = translateTimeToLocalTime(
            baseTime
        )
        assertThat(subjectOne).isEqualTo(subjectTwo)
    }
}