package com.loan.simulator.controller

import com.loan.simulator.domain.usecase.CalculateLoanProposalUseCase
import com.loan.simulator.domain.usecase.RetrieveLoanProposalUseCase
import com.loan.simulator.dto.request.LoanSimulationRequest
import com.loan.simulator.dto.response.LoanProposalResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/loan")
class SimulateLoanController(
    private val calculateLoanProposalUseCase: CalculateLoanProposalUseCase,
    private val retrieveLoanProposalUseCase: RetrieveLoanProposalUseCase
) {

    @PostMapping("/simulate")
    suspend fun simulateLoan(@RequestBody request: LoanSimulationRequest): ResponseEntity<LoanProposalResponse> {

        val proposal = calculateLoanProposalUseCase.execute(request)

        return ResponseEntity.ok(proposal)
    }

    @GetMapping("/{proposalId}")
    suspend fun getLoanProposal(@PathVariable proposalId: String): ResponseEntity<LoanProposalResponse> {
        val proposal = retrieveLoanProposalUseCase.execute(proposalId)

        return ResponseEntity.ok(proposal)
    }

    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }
}