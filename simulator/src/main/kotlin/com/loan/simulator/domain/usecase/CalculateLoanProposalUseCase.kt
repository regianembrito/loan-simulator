package com.loan.simulator.domain.usecase

import com.loan.simulator.domain.entities.LoanProposalEntity
import com.loan.simulator.dto.request.LoanSimulationRequest
import com.loan.simulator.dto.response.LoanProposalResponse
import com.loan.simulator.mapper.LoanProposalResponseMapper
import com.loan.simulator.service.LoanProposalService
import org.springframework.stereotype.Component

@Component
class CalculateLoanProposalUseCase(
    private val loanService: LoanProposalService,
    private val mapper: LoanProposalResponseMapper,
    private val calculateTaxRateUseCase: CalculateTaxRateUseCase
) {
    suspend fun execute(input: LoanSimulationRequest): LoanProposalResponse {

        // Get calculated tax rate
        val taxRate = calculateTaxRateUseCase.execute(input.birthDate).taxRate

        // Calculate monthly payment
        val monthlyRate = taxRate / 12
        val numberOfPayments = input.term
        val monthlyPayment = (input.loanAmount * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -numberOfPayments.toDouble()))

        // Calculate total amount
        val totalAmount = monthlyPayment * numberOfPayments


        // Create proposalEntity
        val proposalEntity = LoanProposalEntity(
            loan_amount = input.loanAmount,
            birth_date = input.birthDate,
            term = input.term,
            monthly_payment = monthlyPayment,
            interest_rate = taxRate,
            total_amount = totalAmount
        )

        // Save proposalEntity to database
        val proposal = loanService.saveLoanProposal(proposalEntity)

        // Return proposal mapped to response
        return mapper.map(proposal)
    }


}