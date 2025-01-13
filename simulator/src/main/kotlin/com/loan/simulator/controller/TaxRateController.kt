package com.loan.simulator.controller

import com.loan.simulator.domain.usecase.CalculateTaxRateUseCase
import com.loan.simulator.dto.response.TaxRateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tax-rate")
class TaxRateController(
    private val calculateTaxRateUseCase: CalculateTaxRateUseCase
) {

    @GetMapping("/{birthDate}")
    @ResponseBody
    suspend fun calculateTaxRate(@PathVariable birthDate: String): ResponseEntity<TaxRateResponse> {
        val tax = calculateTaxRateUseCase.execute(birthDate)
        val taxRate = tax.taxRate * 100
        tax.taxRate = taxRate
        return ResponseEntity.ok(tax)
    }

    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }
}