package com.spothero.employmentTest.domain.enums

import com.spothero.employmentTest.domain.enums.CanonicalUSTZEnum.Companion.fromTzName
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test


class CanonicalUSTZEnumTest {

    @Test
    fun `GIVEN tzName WHEN fromTzName is called THEN the right enums are returned`() {
        val subjectOne: CanonicalUSTZEnum = fromTzName("Pacific/Honolulu")
        val subjectTwo = fromTzName("America/Chicago")
        val subjectThree = fromTzName("America/Honolulu")

        assertThat(subjectOne).isEqualTo(CanonicalUSTZEnum.HONOLULU)
        assertThat(subjectTwo).isEqualTo(CanonicalUSTZEnum.CHICAGO)
        assertThat(subjectThree).isEqualTo(CanonicalUSTZEnum.NOT_APPLICABLE)
    }
}