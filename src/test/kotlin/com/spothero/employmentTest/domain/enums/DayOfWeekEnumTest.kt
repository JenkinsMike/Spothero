package com.spothero.employmentTest.domain.enums

import com.spothero.employmentTest.domain.enums.DayOfWeekEnum.Companion.fromAbbreviation
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class DayOfWeekEnumTest {

    @Test
    fun `GIVEN tzName WHEN fromTzName is called THEN the right enums are returned`() {
        val subjectOne = fromAbbreviation("Mon")
        val subjectTwo = fromAbbreviation("mon")
        val subjectThree = fromAbbreviation("MJ")

        assertThat(subjectOne).isEqualTo(DayOfWeekEnum.MON)
        assertThat(subjectTwo).isEqualTo(DayOfWeekEnum.MON)
        assertThat(subjectThree).isEqualTo(DayOfWeekEnum.NOT_APPLICABLE)
    }
}