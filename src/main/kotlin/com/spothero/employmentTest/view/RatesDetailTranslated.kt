package com.spothero.employmentTest.view

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.joda.time.LocalTime

/**
 * This is effectively a deprecated data class.  I had thought about doing more
 * with some translated Time formats, but I just decided to put them in the db.
 */
@Deprecated("Translated Time objects moved to db as Strings and manipulated after access.")
@JsonIgnoreProperties(ignoreUnknown = true)
data class RatesDetailTranslated(
    val ratesTranslatedList: ArrayList<RatesTranslated> =
        mutableListOf<RatesTranslated>() as ArrayList<RatesTranslated>
)

data class RatesTranslated(
    val daysList: List<String> = emptyList(),
    val timeStartLocalDateTime: LocalTime,// = LocalTime.now().minusMinutes(1),
    val timeEndLocalDateTime: LocalTime,// = LocalTime.now().plusMinutes(1),
    val rate: Int = 0
)
