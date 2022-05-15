package com.anilcanipsalali.accountspring.model

import com.anilcanipsalali.accountspring.model.enums.TransactionType
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

data class Transaction(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,
    val transactionType: TransactionType? = TransactionType.INITIAL,
    val amount: BigDecimal?,
    val transactionDate: LocalDateTime?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account
)