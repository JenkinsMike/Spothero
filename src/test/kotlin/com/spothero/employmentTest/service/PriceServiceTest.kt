package com.spothero.employmentTest.service

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.spothero.employmentTest.controller.PriceController
import com.spothero.employmentTest.entity.RateEntity
import com.spothero.employmentTest.repository.RatesRepository
import com.spothero.employmentTest.view.PriceResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@Disabled()
internal class PriceServiceTest {
    /**
     * If I were to have gotten this to work, I would have tested the following:
     *   1) successful price acquirement and accuracy for valid entries in the GET
     *   2) Validate the PRICE_UNAVAILABLE Custom Exceptions were thrown when necessary
     *      - End dateTime before Start dateTime
     *      - Two different Days
     *      - More than 24h
     *      - Valid criteria result in more than one Price
     */
    private val defaultPriceResponse = PriceResponse(
        price = 723
    )

    private fun mockPriceService(
        result: PriceResponse = defaultPriceResponse
    ): PriceService = mock {
        on {
            getPrice(any(), any())
        } doReturn result
    }

    private val ratesRepository = mock<RatesRepository> {
        on {
            findByLocalEndTimeGreaterThanEqualAndLocalStartTimeLessThanEqual(any(), any())
        } doReturn listOf<RateEntity>()
    }
    private val subject: PriceService = PriceService(
        ratesRepository = ratesRepository
    )

    @Test
    fun `set up a call to findByLocalEndTimeGreaterThanEqualAndLocalStartTimeLessThanEqual`() {
        val mockPriceService = mockPriceService()
        val mockMvc = MockMvcBuilders.standaloneSetup(PriceController(mockPriceService)).build()

    }
}