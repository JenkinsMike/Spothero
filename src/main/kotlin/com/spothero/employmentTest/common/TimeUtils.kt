package com.spothero.employmentTest.common

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import org.joda.time.LocalTime as LocalTime_Joda
import java.time.LocalTime as LocalTime_Java


object TimeUtils {
    fun translateTimeToLocalDateTimeUtil(
        time: String?,
        tz: String = ZonedDateTime.now().zone.id
    ): org.joda.time.LocalDateTime =
        DateTime.parse(
            ZonedDateTime.parse(
                time
            ).toString()
        ).withZone(
            DateTimeZone.forID(
                tz
            )
        ).toLocalDateTime()

    fun translateTimeToLocalTime(
        time: String,
        tz: String = ZonedDateTime.now().zone.toString()
    ): LocalTime_Joda {
        //Make the HHmm into HH:mm
        val timeColonPattern = "HH:mm"
        val timeColonFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(timeColonPattern)
        val colonTime = LocalTime_Java.of(time.take(2).toInt(), time.takeLast(2).toInt())
        val formattedTime = timeColonFormatter.format(colonTime)

        //convert to DateTime
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .parseDefaulting(ChronoField.EPOCH_DAY, 0)
            .toFormatter()
        val dateTime: LocalDateTime = LocalDateTime.parse(formattedTime.toString(), dateTimeFormatter)

        //get standardOffset for non-local times and append to DateTime
        val standardOffset: ZoneOffset = ZoneId.of(tz).rules.getStandardOffset(Instant.now())
        val dateTimeWithOffset: String = "${dateTime}${standardOffset}"

        //translate to LocalDateTime with all the above
        val localDateTime = translateTimeToLocalDateTimeUtil(
            dateTimeWithOffset,
            tz
        )

        return localDateTime.toLocalTime()
    }
}