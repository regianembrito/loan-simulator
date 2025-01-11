package com.loan.simulator.domain.usecase

import com.loan.simulator.dto.response.TaxRateResponse
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Component
class CalculateTaxRateUseCase {
    suspend fun execute(birthDate: String): TaxRateResponse {
        // Calculate age
        val age = calculateAge(birthDate)

        // Calculate and return tax rate
        val taxRate = when {
            age <= 25 -> 0.05
            age in 26..40 -> 0.03
            age in 41..50 -> 0.02
            else -> 0.04
        }

        // Return tax rate as a TaxRateResponse
        return TaxRateResponse(taxRate)
    }

    private fun calculateAge(birthDate: String, format: String = "yyyy-MM-dd"): Int {
        // Convert birthDate to LocalDate
        val birthDateFormatted = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(format))

        // Calculate age
        val now = LocalDate.now()
        return Period.between(birthDateFormatted, now).years
    }
}