package com.loan.simulator.controller

import com.loan.simulator.LoanSimulatorApplication
import com.loan.simulator.domain.usecase.CalculateLoanProposalUseCase
import com.loan.simulator.domain.usecase.RetrieveLoanProposalUseCase
import com.loan.simulator.exception.LoanNotFoundException
import com.loan.simulator.service.LoanProposalService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.*


@SpringBootTest(classes = [LoanSimulatorApplication::class])
class SimulateLoanControllerTest(
    private val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var calculateLoanProposalUseCase: CalculateLoanProposalUseCase

    @MockkBean
    private lateinit var retrieveLoanProposalUseCase: RetrieveLoanProposalUseCase

    @MockkBean
    private lateinit var LoanProposalService: LoanProposalService

    @Test
    fun `should return 404 when proposal not found`() {
        // given
        val proposalId = UUID.randomUUID().toString()

        every { runBlocking { retrieveLoanProposalUseCase.execute(proposalId) } } throws LoanNotFoundException("Loan not found")

        mockMvc.get("/loan/$proposalId")
            .andExpect {
                status { isNotFound() }
                jsonPath("$.status") { value(404) }
                jsonPath("$.message") { value("Loan not found with ID: $proposalId") }
            }
    }
}