package com.spothero.employmentTest.entity

import org.hibernate.Hibernate
import javax.persistence.*


@Entity
@Table(name = "rates")
data class RateEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    var rate: Int = 0,
    val days: String = "",
    val times: String = "",
    val tz: String = "",
    val localStartTime: String = "",
    val localEndTime: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as RateEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
