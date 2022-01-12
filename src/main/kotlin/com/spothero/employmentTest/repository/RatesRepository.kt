package com.spothero.employmentTest.repository

import com.spothero.employmentTest.entity.RateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * I had findByDaysLikeAndLocalEndTimeGreaterThanEqualAndLocalStartTimeLessThanEqual
 * here, but it seemed to consider "Wed" and "wed" as not-like-enough-to-validate, so
 * I let Hibernate write the SQL based on times and then code filter out the
 * day, ignoring case.
 */
@Repository
interface RatesRepository : JpaRepository<RateEntity, Long> {
    fun findByLocalEndTimeGreaterThanEqualAndLocalStartTimeLessThanEqual(
        startLocalTime: String,
        endLocalTime: String
    ): List<RateEntity>?
}
