package com.loan.simulator.usecase

import com.loan.simulator.domain.usecase.RetrieveLoanProposalUseCase
import com.loan.simulator.dto.response.LoanProposalResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class RetrieveLoanProposalUseCaseTest {

    private val retrieveLoanProposalUseCase = mockk<RetrieveLoanProposalUseCase>()

    @Test
    fun `should retrieve loan proposal correctly`() = runBlocking {
        val proposalId = "0cdc9ea8-71eb-445a-891a-77c93c723213"
        val expectedResponse = LoanProposalResponse(UUID.fromString(proposalId), 0.03, 846.94, 10163.24)

        every { runBlocking { retrieveLoanProposalUseCase.execute(proposalId) } } returns expectedResponse

        val actualResponse = retrieveLoanProposalUseCase.execute(proposalId)

        assertEquals(expectedResponse, actualResponse)
    }
}