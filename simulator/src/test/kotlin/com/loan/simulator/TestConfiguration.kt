package com.loan.simulator

import com.fasterxml.jackson.databind.ObjectMapper
import com.loan.simulator.domain.usecase.CalculateLoanProposalUseCase
import com.loan.simulator.domain.usecase.RetrieveLoanProposalUseCase
import com.ninjasquad.springmockk.MockkBean
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean

@SpringBootConfiguration
class TestConfiguration {

    @MockkBean
    lateinit var calculateLoanProposalUseCase: CalculateLoanProposalUseCase

    @MockkBean
    lateinit var retrieveLoanProposalUseCase: RetrieveLoanProposalUseCase

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}
