package com.loan.simulator.dto.request

import org.jetbrains.annotations.NotNull

data class LoanSimulationRequest(
    @field:NotNull
    val loanAmount: Double,
    @field:NotNull
    val birthDate: String,
    @field:NotNull
    val term: Int
)