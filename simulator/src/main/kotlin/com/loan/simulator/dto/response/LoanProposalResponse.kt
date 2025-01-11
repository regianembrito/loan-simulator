package com.loan.simulator.dto.response

import java.util.*

data class LoanProposalResponse(
    val proposalId: UUID? = null,
    val interestRate: Double?,
    val monthlyPayment: Double?,
    val totalAmount: Double?
)