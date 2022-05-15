package com.anilcanipsalali.accountspring.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val balance: BigDecimal? = BigDecimal.ZERO,
    val creationDate: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val transaction: Set<Transaction> = HashSet()
)