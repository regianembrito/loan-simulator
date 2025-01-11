package com.loan.simulator.mapper

import com.loan.simulator.domain.entities.LoanProposalEntity
import com.loan.simulator.dto.response.LoanProposalResponse
import org.springframework.stereotype.Component
import java.math.RoundingMode

@Component
class LoanProposalResponseMapper(
) : Mapper<LoanProposalEntity, LoanProposalResponse> {
    override fun map(t: LoanProposalEntity): LoanProposalResponse {
        return LoanProposalResponse(
            proposalId = t.proposal_id,
            interestRate = t.interest_rate,
            monthlyPayment = t.monthly_payment?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble(),
            totalAmount = t.total_amount?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)?.toDouble()

        )
    }
}