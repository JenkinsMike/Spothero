package com.spothero.employmentTest.view

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class RatesDetail(
    val rates: ArrayList<Rates> =
        mutableListOf<Rates>() as ArrayList<Rates>
)

data class Rates(
    val days: String,
    val times: String,
    val tz: String,
    val price: Int
)
