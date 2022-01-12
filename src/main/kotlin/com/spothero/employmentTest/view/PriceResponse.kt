package com.spothero.employmentTest.view

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class PriceResponse(
    val price: Int = 0
)
