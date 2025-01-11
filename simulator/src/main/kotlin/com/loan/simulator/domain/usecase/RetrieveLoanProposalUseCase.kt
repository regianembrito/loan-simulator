package com.loan.simulator.domain.usecase

import com.loan.simulator.dto.response.LoanProposalResponse
import com.loan.simulator.mapper.LoanProposalResponseMapper
import com.loan.simulator.service.LoanProposalService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RetrieveLoanProposalUseCase(
    private val loanService: LoanProposalService,
    private val mapper: LoanProposalResponseMapper
) {

    suspend fun execute(proposalId: String): LoanProposalResponse {
        // Retrieve loan proposal
        val proposal = loanService.getProposalById(UUID.fromString(proposalId))

        // Return proposal mapped to response
        return mapper.map(proposal)
    }
}