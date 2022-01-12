package com.spothero.employmentTest.controller

import com.spothero.employmentTest.service.RatesService
import com.spothero.employmentTest.view.RatesDetail
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class RatesController(
    @Autowired private val ratesService: RatesService,
) {
    @Operation(
        description = "PUT a new 'Rates' JSON that overwrites currently stored 'Rates'."
    )
    @PutMapping(value = ["/v1/rates"])
    fun putRatesOverride(
        @RequestBody request: RatesDetail
    ) {
        ratesService.saveRates(request)
    }

    @Operation(
        description = "GET all currently available 'Rates'."
    )
    @GetMapping(value = ["/v1/rates"])
    @Throws()
    fun getRates(): RatesDetail {
        return ratesService.getRates()
    }
}
