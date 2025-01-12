package com.loan.simulator.usecase

import com.loan.simulator.domain.usecase.CalculateLoanProposalUseCase
import com.loan.simulator.dto.request.LoanSimulationRequest
import com.loan.simulator.dto.response.LoanProposalResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class CalculateLoanProposalUseCaseTest {

    private val calculateLoanProposalUseCase = mockk<CalculateLoanProposalUseCase>()

    @Test
    fun `should calculate loan proposal correctly`() = runBlocking {
        val request = LoanSimulationRequest(10000.00, "1990-01-01", 12)
        val expectedResponse = LoanProposalResponse(UUID.randomUUID(), 0.03, 846.94, 10163.24)

        every { runBlocking { calculateLoanProposalUseCase.execute(request) } } returns expectedResponse

        val actualResponse = calculateLoanProposalUseCase.execute(request)

        assertEquals(expectedResponse, actualResponse)
    }
}