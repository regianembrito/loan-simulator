package com.loan.simulator.exception

data class ErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: Long
)