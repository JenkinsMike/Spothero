package com.spothero.employmentTest.domain.enums


/**
 * An unnecessary enum class written before a more fulsome knowledge of
 * Java Time and Joda Time.
 *
 * I am leaving it for the turn-in.  I thought I would end up using this as some validation,
 * but it is unnecessary.  I limited the entries to Canonical American times.
 */
enum class CanonicalUSTZEnum(
    val tzName: String,
    val offsetUTC: String,
    val offsetUTCDST: String
) {
    ADAK("America/Adak", "-10:00", "-09:00"),
    ANCHORAGE("America/Anchorage", "-09:00", "-08:00"),
    BOISE("America/Boise", "-07:00", "-06:00"),
    CHICAGO("America/Chicago", "-06:00", "-05:00"),
    DENVER("America/Denver", "-07:00", "-06:00"),
    DETROIT("America/Detroit", "-06:00", "-05:00"),
    INDIANA_INDIANAPOLIS("America/Indiana/Indianapolis", "-05:00", "-04:00"),
    INDIANA_KNOX("America/Indiana/Knox", "-06:00", "-05:00"),
    INDIANA_MARENGO("America/Indiana/Marengo", "-05:00", "-04:00"),
    INDIANA_PETERSBURG("America/Indiana/Petersburg", "-05:00", "-04:00"),
    INDIANA_TELLCITY("America/Indiana/Tell_City", "-06:00", "-05:00"),
    INDIANA_VEVAY("America/Indiana/Vevay", "-05:00", "-04:00"),
    INDIANA_VINCENNES("America/Indiana/Vincennes", "-05:00", "-04:00"),
    INDIANA_WINAMAC("America/Indiana/Winamac", "-05:00", "-04:00"),
    JUNEAU("America/Juneau", "-09:00", "-08:00"),
    KENTUCKY_LOUISVILLE("America/Kentucky/Louisville", "-05:00", "-04:00"),
    KENTUCKY_MONTICELLO("America/Kentucky/Monticello", "-05:00", "-04:00"),
    LOSANGELES("America/Los_Angeles", "-08:00", "-07:00"),
    MENOMINEE("America/Menominee", "-06:00", "-05:00"),
    METLAKATLA("America/Metlakatla", "-09:00", "-08:00"),
    NEWYORK("America/New_York", "-05:00", "-04:00"),
    NOME("America/Nome", "-09:00", "-08:00"),
    SITKA("America/Sitka", "-09:00", "-08:00"),
    YAKUTAT("America/Yakutat", "-09:00", "-08:00"),
    PHOENIX("America/Phoenix", "-07:00", "-07:00"),
    HONOLULU("Pacific/Honolulu", "-10:00", "-10:00"),
    NOT_APPLICABLE("", "", "");

    companion object {
        @JvmStatic
        fun allValues() = values()
        fun fromTzName(value: String): CanonicalUSTZEnum =
            values().find {
                it.tzName.equals(value, ignoreCase = true)
            } ?: NOT_APPLICABLE
    }
}
