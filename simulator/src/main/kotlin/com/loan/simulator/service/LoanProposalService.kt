package com.loan.simulator.service

import com.loan.simulator.domain.entities.LoanProposalEntity
import com.loan.simulator.exception.LoanNotFoundException
import com.loan.simulator.repository.LoanProposalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoanProposalService(
    private val repository: LoanProposalRepository
) {
    suspend fun getProposalById(proposalId: UUID): LoanProposalEntity {
        return withContext(Dispatchers.IO) {
            repository.findById(proposalId) ?: throw LoanNotFoundException("Loan not found with ID: $proposalId")
        }
    }

    suspend fun saveLoanProposal(proposal: LoanProposalEntity): LoanProposalEntity {
        return withContext(Dispatchers.IO) {
            repository.save(proposal)
        }
    }
}