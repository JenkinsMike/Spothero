package com.spothero.employmentTest.domain.enums


/**
 * An unnecessary enum class written before a more fulsome knowledge of
 * Java Time and Joda Time.
 *
 * I am leaving it for the turn-in.  I thought I would end up using this as some validation,
 * but it is unnecessary.  I thought I might need to change "Monday" to "Mon" or something,
 * Java Time and Joda Time, once learned a bit more, did all of this for me.
 */
enum class DayOfWeekEnum(
    val dayFullName: String,
    val dayAbbreviation: String,
    val dayShorthand: String,
    val dayPositionInWeek: Int
) {
    MON("Monday", "Mon", "M", 1),
    TUE("Tuesday", "Tues", "Tu", 2),
    WED("Wednesday", "Wed", "W", 3),
    THU("Thursday", "Thus", "Th", 4),
    FRI("Friday", "Fri", "F", 5),
    SAT("Saturday", "Sat", "Sa", 6),
    SUN("Sunday", "Sun", "Su", 7),
    NOT_APPLICABLE("", "", "", 0);

    companion object {
        @JvmStatic
        fun allValues() = values()

        fun fromAbbreviation(value: String): DayOfWeekEnum =
            values().find {
                it.dayAbbreviation.equals(value, ignoreCase = true)
            } ?: NOT_APPLICABLE
    }
}
