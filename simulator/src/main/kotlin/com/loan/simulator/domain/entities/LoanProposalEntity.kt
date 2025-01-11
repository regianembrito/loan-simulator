package com.loan.simulator.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("loan_proposal")
data class LoanProposalEntity(
    @Id
    val proposal_id: UUID? = null,
    val loan_amount: Double, // valor do empréstimo
    val birth_date: String, // data de nascimento
    val term: Int, // prazo
    val monthly_payment: Double?, // valor da parcela
    val interest_rate: Double?, // taxa de juros
    val total_amount: Double? // valor total
)