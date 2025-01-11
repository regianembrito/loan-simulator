package com.loan.simulator.repository

import com.loan.simulator.domain.entities.LoanProposalEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LoanProposalRepository : CoroutineCrudRepository<LoanProposalEntity, UUID>