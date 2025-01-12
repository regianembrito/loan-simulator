package com.loan.simulator

import com.fasterxml.jackson.databind.ObjectMapper
import com.loan.simulator.domain.usecase.CalculateLoanProposalUseCase
import com.loan.simulator.domain.usecase.RetrieveLoanProposalUseCase
import com.loan.simulator.dto.request.LoanSimulationRequest
import com.loan.simulator.dto.response.LoanProposalResponse
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfiguration::class)
class SimulateLoanControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var calculateLoanProposalUseCase: CalculateLoanProposalUseCase

    @MockkBean
    private lateinit var retrieveLoanProposalUseCase: RetrieveLoanProposalUseCase

    @Test
    fun `should simulate loan successfully`(): Unit = runBlocking {
        val request = LoanSimulationRequest( 10000.00,  "1990-01-01",  12)
        val response = LoanProposalResponse(proposalId = UUID.randomUUID(), interestRate = 0.03, monthlyPayment = 846.94, totalAmount = 10163.24)

        every { runBlocking { calculateLoanProposalUseCase.execute(request) } } returns response

        val requestJson = objectMapper.writeValueAsString(request)

        mockMvc.post("/loan/simulate") {
            contentType = MediaType.APPLICATION_JSON
            content = requestJson
        }.andExpect {
            status { isOk() }
            content {
                jsonPath("$.proposalId") { value(response.proposalId.toString()) }
                jsonPath("$.interestRate") { value(response.interestRate) }
                jsonPath("$.monthlyPayment") { value(response.monthlyPayment) }
                jsonPath("$.totalAmount") { value(response.totalAmount) }
            }
        }
    }

    @Test
    fun `should retrieve loan proposal successfully`(): Unit = runBlocking {
        val proposalId = UUID.randomUUID().toString()
        val response = LoanProposalResponse(proposalId = UUID.randomUUID(), interestRate = 0.03, monthlyPayment = 846.94, totalAmount = 10163.24)

        every { runBlocking { retrieveLoanProposalUseCase.execute(proposalId) } } returns response

        mockMvc.get("/loan/$proposalId")
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.proposalId") { value(response.proposalId.toString()) }
                    jsonPath("$.interestRate") { value(response.interestRate) }
                    jsonPath("$.monthlyPayment") { value(response.monthlyPayment) }
                    jsonPath("$.totalAmount") { value(response.totalAmount) }
                }
            }
    }
}