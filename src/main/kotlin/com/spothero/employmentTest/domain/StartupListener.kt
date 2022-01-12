package com.spothero.employmentTest.domain

import com.spothero.employmentTest.service.RatesService
import com.spothero.employmentTest.view.Rates
import com.spothero.employmentTest.view.RatesDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


/**
 * I know the way it is implemented is not the most elegant.  I thought about using Flyway,
 * but wanted to use an event listener instead.  I wanted to use a file in Resources, but
 * I only know how to do it in an inputStream way.  I have a way that works here, but want
 * better ways.  As I would probably tag this in my codebase, I am going to here, and
 * identify this as code that can be improved.
 */
@Component
@Order(0)
class StartupListener(
    @Autowired private val ratesService: RatesService
) : ApplicationListener<ApplicationReadyEvent> {
    @Override
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        /**
         * ToDo | Find a way to utilize either Flyway migrations or use the file in
         *        resources to fill the database on application startup without having
         *        to put that as a coded Object.
         */
        //val fileContent = this::class.java.classLoader.getResource("rate-data.json").readText()
        //println("fileContent is $fileContent")

        val initialRates: ArrayList<Rates> = arrayListOf(
            Rates(
                days = "mon,tues,thurs",
                times = "0900-2100",
                tz = "America/Chicago",
                price = 1500
            ),
            Rates(
                days = "fri,sat,sun",
                times = "0900-2100",
                tz = "America/Chicago",
                price = 2000
            ),
            Rates(
                days = "wed",
                times = "0600-1800",
                tz = "America/Chicago",
                price = 1750
            ),
            Rates(
                days = "mon,wed,sat",
                times = "0100-0500",
                tz = "America/Chicago",
                price = 1000
            ),
            Rates(
                days = "sun,tues,",
                times = "0100-0700",
                tz = "America/Chicago",
                price = 925
            )
        )
        val ratesDetail: RatesDetail = RatesDetail().copy(rates = initialRates)

        ratesService.saveRates(ratesDetail)
    }
}
